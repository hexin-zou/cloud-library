package com.example.springboot.api;

import com.example.springboot.api.request.BaseRequest;
import com.example.springboot.api.entity.User;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface IUserService {

    List<User> list();

    PageInfo<User> page(BaseRequest baseRequest);

    void save(User user);

    User getById(Integer id);

    void update(User user);

    void deleteById(Integer id);

    void handleAccount(User user);

    User getByUsername(String username);

    void updateById(User user);

}







































































































