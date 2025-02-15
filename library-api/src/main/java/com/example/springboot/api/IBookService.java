package com.example.springboot.api;

import com.example.springboot.api.request.BaseRequest;
import com.example.springboot.api.entity.Book;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface IBookService {

    List<Book> list();

    PageInfo<Book> page(BaseRequest baseRequest);

    void save(Book obj);

    Book getById(Integer id);

    void update(Book obj);

    void deleteById(Integer id);

    Book getByNo(String bookNo);

    void updateById(Book user);

    void updateNumByNo(String bookNo);

}
