package com.example.portal.dto;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class UserAllDTO {
    private Long id;

    private String name;

    private String photo;
}