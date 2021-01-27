package com.jx.tdmp.controller;

import cn.hutool.core.lang.Assert;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.jx.tdmp.common.dto.LoginDto;
import com.jx.tdmp.common.lang.ResponseResult;
import com.jx.tdmp.constant.ResultCode;
import com.jx.tdmp.entity.User;
import com.jx.tdmp.utils.JwtUtils;
import com.jx.tdmp.service.Interface.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

@RestController
public class AccountController {

    @Autowired
    JwtUtils jwtUtils;
    @Autowired
    UserService userService;
    /**
     * 默认账号密码：suki / 123456
     *
     */
    @CrossOrigin
    @PostMapping("/login")
    @ResponseBody
    public ResponseResult login(@Validated @RequestBody LoginDto loginDto, HttpServletResponse response) {
        User user = userService.getOne(new QueryWrapper<User>().eq("user_name", loginDto.getUsername()));
        Assert.notNull(user, "用户不存在");
        if(!user.getPasswd().equals(loginDto.getPassword())){
            return new ResponseResult(ResultCode.fail);
        }
//        if(!user.getPasswd().equals(SecureUtil.md5(loginDto.getPassword()))) {
//            //TODO:ResultCode定义
//            return new ResponseResult(ResultCode.fail);
//        }
        String jwt = jwtUtils.generateToken(Long.parseLong(user.getUserId()));
        response.setHeader("Authorization", jwt);
        response.setHeader("Access-Control-Expose-Headers", "Authorization");
        // 用户可以另一个接口
        // TODO: ResultCode
        return new ResponseResult(ResultCode.succeed);
//        return Result.succ(MapUtil.builder()
//                .put("id", user.getId())
//                .put("username", user.getUsername())
//                .put("avatar", user.getAvatar())
//                .put("email", user.getEmail())
//                .map()
//        );
    }

    // 退出
    @GetMapping("/logout")
    @RequiresAuthentication
    @ResponseBody
    public ResponseResult logout() {
        SecurityUtils.getSubject().logout();
        // TODO ResultCode
        return new ResponseResult(ResultCode.succeed);
    }
}
