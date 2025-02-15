package com.example.springboot.api.request;

import lombok.Data;

@Data
public class BookPageRequest extends BaseRequest{
    private String name;
    private String bookNo;
}
