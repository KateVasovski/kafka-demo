package com.example.kafka.demo.model;

import lombok.Data;

@Data
public class UserDto {
    private Long age;
    private String name;
    private Address address;
}