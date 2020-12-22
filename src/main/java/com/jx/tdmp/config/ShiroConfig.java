package com.jx.tdmp.config;

import com.jx.tdmp.security.AccountRealm;
import com.jx.tdmp.security.JwtFilter;
import org.apache.shiro.mgt.DefaultSessionStorageEvaluator;
import org.apache.shiro.mgt.DefaultSubjectDAO;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.session.mgt.SessionManager;
import org.apache.shiro.session.mgt.eis.JavaUuidSessionIdGenerator;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.spring.web.config.DefaultShiroFilterChainDefinition;
import org.apache.shiro.spring.web.config.ShiroFilterChainDefinition;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.crazycake.shiro.RedisCacheManager;
import org.crazycake.shiro.RedisManager;
import org.crazycake.shiro.RedisSessionDAO;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


import javax.servlet.Filter;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * shiro启用注解拦截控制器
 *https://segmentfault.com/a/1190000022777009
 * https://zhuanlan.zhihu.com/p/82375794
 */
@Configuration
public class ShiroConfig {
//    @Autowired
//    JwtFilter jwtFilter;

    @Bean
    @ConfigurationProperties(prefix = "spring.redis")
    public RedisManager redisManager() {
        RedisManager redisManager = new RedisManager(); // crazycake 实现
//        redisManager.setHost("${host}");
//        redisManager.setTimeout(Integer.parseInt("${timeout}"));
        return redisManager;
    }

    @Bean
    public JavaUuidSessionIdGenerator sessionIdGenerator() {
        return new JavaUuidSessionIdGenerator();
    }

    @Bean
    public RedisSessionDAO sessionDAO() {
        RedisSessionDAO sessionDAO = new RedisSessionDAO(); // crazycake 实现
        sessionDAO.setRedisManager(redisManager());
        sessionDAO.setSessionIdGenerator(sessionIdGenerator()); // Session ID 生成器
        return sessionDAO;
    }

    @Bean
    public SessionManager sessionManager(RedisSessionDAO redisSessionDAO) {
        DefaultWebSessionManager sessionManager = new DefaultWebSessionManager();
        sessionManager.setSessionDAO(redisSessionDAO);
        return sessionManager;
    }


    @Bean
    public RedisCacheManager redisCacheManager() {
        RedisCacheManager cacheManager = new RedisCacheManager(); // crazycake 实现
        cacheManager.setRedisManager(redisManager());
        return cacheManager;
    }

    @Bean
    public DefaultWebSecurityManager securityManager(AccountRealm accountRealm,
                                                     SessionManager sessionManager,
                                                     RedisCacheManager redisCacheManager) {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager(accountRealm);
//        securityManager.setSessionManager(sessionManager);
//        securityManager.setCacheManager(redisCacheManager);
        /*
         * 关闭shiro自带的session，详情见文档
         */
        DefaultSubjectDAO subjectDAO = new DefaultSubjectDAO();
        DefaultSessionStorageEvaluator defaultSessionStorageEvaluator = new DefaultSessionStorageEvaluator();
        defaultSessionStorageEvaluator.setSessionStorageEnabled(false);
        subjectDAO.setSessionStorageEvaluator(defaultSessionStorageEvaluator);
        securityManager.setSubjectDAO(subjectDAO);
        return securityManager;
    }

    @Bean
    public ShiroFilterChainDefinition shiroFilterChainDefinition() {
        DefaultShiroFilterChainDefinition chainDefinition = new DefaultShiroFilterChainDefinition();
        chainDefinition.addPathDefinition("/login", "anon");
        chainDefinition.addPathDefinition("/users", "anon");
        Map<String, String> filterMap = new LinkedHashMap<>();
        filterMap.put("/**", "jwt"); // 主要通过注解方式校验权限
        chainDefinition.addPathDefinitions(filterMap);
        return chainDefinition;
    }


    // factoryBean中通过两张表来进行filter，分别是：filterName--filter;路径--filterName。从而实现认证与鉴权
    // filter包括originFilter以及自定义filter，filterchain先执行originFilter再执行自定义filter
    // originFilter:
//    public enum DefaultFilter {
//        anon(AnonymousFilter.class),
//        authc(FormAuthenticationFilter.class),
//        authcBasic(BasicHttpAuthenticationFilter.class),
//        logout(LogoutFilter.class),
//        noSessionCreation(NoSessionCreationFilter.class),
//        perms(PermissionsAuthorizationFilter.class),
//        port(PortFilter.class),
//        rest(HttpMethodPermissionFilter.class),
//        roles(RolesAuthorizationFilter.class),
//        ssl(SslFilter.class),
//        user(UserFilter.class);
//    }
    @Bean("shiroFilterFactoryBean")
    public ShiroFilterFactoryBean shiroFilterFactoryBean(SecurityManager securityManager,
                                                         ShiroFilterChainDefinition shiroFilterChainDefinition) {
        ShiroFilterFactoryBean shiroFilter = new ShiroFilterFactoryBean();
        shiroFilter.setSecurityManager(securityManager);
//        shiroFilter.setLoginUrl("/login");
//        shiroFilter.setSuccessUrl("/index");
        // 将filter加入到factoryBean中
        Map<String, Filter> filters = new HashMap<>();
        filters.put("jwt", jwtFilter());
        shiroFilter.setFilters(filters);
        // 获取路径和filer的对应表并加入到factoryBean中
        Map<String, String> filterMap = shiroFilterChainDefinition.getFilterChainMap();  // 可以带参数
        shiroFilter.setFilterChainDefinitionMap(filterMap);
        return shiroFilter;
    }
    @Bean
    public JwtFilter jwtFilter(){
        return new JwtFilter();
    }

    // 开启注解代理（默认好像已经开启，可以不要）
    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(SecurityManager securityManager) {
        AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
        authorizationAttributeSourceAdvisor.setSecurityManager(securityManager);
        return authorizationAttributeSourceAdvisor;
    }

    @Bean
    public static DefaultAdvisorAutoProxyCreator getDefaultAdvisorAutoProxyCreator() {
        DefaultAdvisorAutoProxyCreator creator = new DefaultAdvisorAutoProxyCreator();
        creator.setUsePrefix(true);
        return creator;
    }
}