package com.example.springboot.controller;

import com.example.springboot.api.IUserService;
import com.example.springboot.common.Result;
import com.example.springboot.api.request.UserPageRequest;
import com.example.springboot.api.entity.User;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    IUserService userService;

    @PostMapping("/save")
    public Result save(@RequestBody User user) {
        userService.save(user);
        return Result.success();
    }

    @PostMapping("/account")
    public Result account(@RequestBody User user) {
        userService.handleAccount(user);
        return Result.success();
    }

    @PutMapping("/update")
    public Result update(@RequestBody User user) {
        userService.update(user);
        return Result.success();
    }

    @DeleteMapping("/delete/{id}")
    public Result delete(@PathVariable Integer id) {
        userService.deleteById(id);
        return Result.success();
    }

    @GetMapping("/{id}")
    public Result getById(@PathVariable Integer id) {
        User user = userService.getById(id);
        return Result.success(user);
    }

    @GetMapping("/list")
    public Result list() {
        List<User> users = userService.list();
        return Result.success(users);
    }

    @GetMapping("/page")
    public Result page(UserPageRequest userPageRequest) {
        PageInfo<User> pageInfo = userService.page(userPageRequest);
        return Result.success(pageInfo);
    }

    @GetMapping("/byUserNo/{userNo}")
    public User getByUsername(@PathVariable("userNo") String userNo) {
        User user = userService.getByUsername(userNo);
        return user;
    }

    @PutMapping("/updateById")
    public void updateById(@RequestBody User user) {
        userService.updateById(user);
    }

}
