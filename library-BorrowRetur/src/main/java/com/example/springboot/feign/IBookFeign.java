package com.example.springboot.feign;

import com.example.springboot.entity.Book;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "library-book",path = "/book")
public interface IBookFeign {
    @GetMapping("/getByNo/{bookNo}")
    public Book getByNo(@PathVariable("bookNo") String bookNo);

    @PutMapping("/updateById")
    public void updateById(@RequestBody Book user);

    @PutMapping("/{updateNumByNo}")
    public void updateNumByNo(@PathVariable("bookNo") String bookNo);

}

