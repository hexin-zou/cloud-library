package com.example.springboot.mapper;

import com.example.springboot.api.request.BaseRequest;
import com.example.springboot.api.entity.Book;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BookMapper {

    List<Book> list();

    List<Book> listByCondition(BaseRequest baseRequest);

    void save(Book obj);

    Book getById(Integer id);

    void updateById(Book user);

    void deleteById(Integer id);

    Book getByNo(String bookNo);

    void updateNumByNo(String bookNo);
}

