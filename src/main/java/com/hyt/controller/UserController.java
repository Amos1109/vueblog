package com.hyt.controller;


import com.hyt.common.lang.Result;
import com.hyt.entity.User;
import com.hyt.service.UserService;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author AmosHong
 * @since 2020-07-23
 */
@RestController
@RequestMapping("/user")
public class UserController {
        @Autowired
    UserService userService;
        @RequiresAuthentication
        @GetMapping("/index")
        public Result index(){
            User user= userService.getById(1L);
            return Result.success(200,"操作成功",user);
        }

    @PostMapping("/save")
    public Result save(@Validated @RequestBody User user){
        return Result.success(user);
    }
}
