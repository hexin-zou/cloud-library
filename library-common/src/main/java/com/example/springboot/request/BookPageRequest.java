package com.example.springboot.request;

import lombok.Data;

@Data
public class BookPageRequest extends BaseRequest{
    private String name;
    private String bookNo;
}
