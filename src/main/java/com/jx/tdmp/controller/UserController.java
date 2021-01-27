package com.jx.tdmp.controller;

import com.jx.tdmp.common.lang.ResponseResult;
import com.jx.tdmp.constant.ResultCode;
import com.jx.tdmp.entity.User;
import com.jx.tdmp.exception.UserException;
import com.jx.tdmp.service.Interface.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * restful api
 * /users/ get
 * /users  put
 * /users/{id} get
 * /users/{id} put
 * /users/{id} delete
 * </p>
 *
 * @since 2020-12-20
 */
@RestController
@RequestMapping("/users")
public class UserController {

    // 初始化一些需要的service
    // private XXXService xxxService
    @Autowired
    @Resource(name="userServiceImpl")
    private UserService userService;

    @PostMapping
    @ResponseBody
    public ResponseResult<User> createUser(@Validated @RequestBody User user) {
        if (user.getUserName() == null) {
            throw new UserException(ResultCode.NAME_CANNOT_BE_NULL);
        }
        boolean response = userService.save(user);

        if (response) {
            return new ResponseResult<>(ResultCode.ADD_ITEM_SUCCESS, user);
        } else {
            return new ResponseResult<>(ResultCode.ADD_ITEM_failure, user);
        }
    }

    @RequestMapping(method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public List<User> listUsers() {
        return userService.listUsers();

    }
    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public User getUser(@PathVariable("id") String id) {
        return userService.getUserById(id);

    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces = "application/json")
    @ResponseBody
    public boolean deleteUser(@PathVariable("id") String id) {
        return userService.deleteUser(id);

    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT, produces = "application/json")
    @ResponseBody
    public User updateUser(@PathVariable("id") String id) {
        return userService.updateUser(id);

    }

}
