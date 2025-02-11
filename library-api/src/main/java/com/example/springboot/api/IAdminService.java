package com.example.springboot.api;

import com.example.springboot.dto.LoginDTO;
import com.example.springboot.request.BaseRequest;
import com.example.springboot.request.LoginRequest;
import com.example.springboot.request.PasswordRequest;
import com.example.springboot.entity.Admin;
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






































































































