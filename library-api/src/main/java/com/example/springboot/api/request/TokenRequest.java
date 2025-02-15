package com.example.springboot.api.request;

import lombok.Data;

@Data
public class TokenRequest {
    private String adminId;
    private String sign;
    private Integer hour;
}
