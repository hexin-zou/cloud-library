package com.example.springboot.controller;

import com.example.springboot.entity.Admin;
import com.example.springboot.utils.TokenUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/token")
public class TokenController {
    @RequestMapping("/genToken")
    public String genToken(@RequestBody Admin request) {
        return TokenUtils.genToken(request.getId().toString(), request.getPassword());
    }

    @RequestMapping("/getCurrentAdmin")
    public Admin getCurrentAdmin() {
        return TokenUtils.getCurrentAdmin();
    }

}
