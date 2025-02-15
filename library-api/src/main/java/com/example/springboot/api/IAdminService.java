package com.example.springboot.api;

import com.example.springboot.api.dto.LoginDTO;
import com.example.springboot.api.request.BaseRequest;
import com.example.springboot.api.request.LoginRequest;
import com.example.springboot.api.request.PasswordRequest;
import com.example.springboot.api.entity.Admin;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface IAdminService {

    List<Admin> list();

    PageInfo<Admin> page(BaseRequest baseRequest);

    void save(Admin obj);

    Admin getById(Integer id);

    void update(Admin obj);

    void deleteById(Integer id);

    LoginDTO login(LoginRequest request);

    void changePass(PasswordRequest request);

}






































































































