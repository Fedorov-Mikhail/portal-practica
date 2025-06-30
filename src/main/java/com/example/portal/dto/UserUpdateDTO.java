package com.example.portal.dto;

import lombok.Data;

import java.time.LocalDate;


@Data
public class UserUpdateDTO {
    private String name;
    private LocalDate birthday;
    private LocalDate startWork;
    private String telegram;
    private String city;
    private String email;
    private String phoneNumber;
}