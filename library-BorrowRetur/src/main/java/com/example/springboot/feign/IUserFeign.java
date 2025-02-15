package com.example.springboot.feign;

import com.example.springboot.api.entity.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "library-user",path = "/user")
public interface IUserFeign {

    @GetMapping("/byUserNo/{userNo}")
    public User getByUsername(@PathVariable("userNo") String userNo);

    @PutMapping("/updateById")
    public void updateById(@RequestBody User user);

}
