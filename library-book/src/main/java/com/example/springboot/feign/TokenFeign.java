package com.example.springboot.feign;

import com.example.springboot.api.entity.Admin;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient(name = "library-admin",path = "/token")
public interface TokenFeign {

    @RequestMapping ("/genToken")
    public String genToken(@RequestBody Admin admin);

    @GetMapping("/getCurrentAdmin")
    public Admin getCurrentAdmin();

}
