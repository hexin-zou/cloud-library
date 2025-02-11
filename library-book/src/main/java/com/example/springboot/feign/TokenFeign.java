package com.example.springboot.feign;

import com.example.springboot.entity.Admin;
import com.example.springboot.request.TokenRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient(name = "library-admin",path = "/token")
public interface TokenFeign {

    @RequestMapping("/genToken")
    public String genToken(@RequestBody Admin request);

    @RequestMapping("/getCurrentAdmin")
    public Admin getCurrentAdmin();

}
