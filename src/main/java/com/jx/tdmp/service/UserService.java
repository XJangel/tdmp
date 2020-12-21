package com.jx.tdmp.service;

import com.jx.tdmp.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @since 2020-12-20
 */
public interface UserService extends IService<User> {
    List<User> listUsers();
    User getUser(String id);
    User createUser(User user);
    User updateUser(String id);
    boolean deleteUser(String id);

}
