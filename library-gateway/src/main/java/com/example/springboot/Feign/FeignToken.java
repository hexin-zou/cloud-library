package com.example.springboot.Feign;

import com.example.springboot.api.entity.Admin;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "library-admin",path = "/admin")
public interface FeignToken {

    @GetMapping("/token/{id}")
    public Admin getById2(@PathVariable("id") Integer id);


}
