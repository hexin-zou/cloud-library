package com.example.springboot.api;

import com.example.springboot.api.request.BaseRequest;
import com.example.springboot.api.entity.Category;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface ICategoryService {

    List<Category> list();

    PageInfo<Category> page(BaseRequest baseRequest);

    void save(Category obj);

    Category getById(Integer id);

    void update(Category obj);

    void deleteById(Integer id);

}
