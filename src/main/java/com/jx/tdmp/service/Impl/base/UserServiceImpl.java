package com.jx.tdmp.service.Impl.base;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.jx.tdmp.entity.User;
import com.jx.tdmp.mapper.UserMapper;
import com.jx.tdmp.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * UserService实现类
 * </p>
 * @author Jiejie Xu
 * @since 2020-12-20
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
    // 这里报错并不影响运行，可能是ide问题
    @Autowired
    private UserMapper userMapper;

    @Override
    public List<User> listUsers() {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
//        查询指定字段。显示效果为其他非被查询字段皆为null
//        queryWrapper.select(User.class , e->!e.getColumn().equals("passwd"));
        return userMapper.selectList(queryWrapper);


    }

    @Override
    public User getUserById(String id) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.select("user_id").gt("user_id", id);
        return userMapper.selectById(queryWrapper);
    }

    @Override
    public User createUser(User user) {
        return null;
    }

    @Override
    public User updateUser(String id) {
        return null;
    }

    @Override
    public boolean deleteUser(String id) {
        return false;
    }

}
