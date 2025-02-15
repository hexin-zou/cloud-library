package com.example.springboot.api.request;

import lombok.Data;

@Data
public class CategoryPageRequest extends BaseRequest{
    private String name;
}
