package com.example.portal.dto;


import lombok.Data;
import lombok.experimental.Accessors;

import java.time.LocalDate;

@Data
@Accessors(chain = true)
public class BirthdayDTO {
    private Long id;
    private String name;
    private LocalDate birthday;
    private String photo;
}