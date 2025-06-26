package com.example.portal.dto;

import lombok.Data;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

@Data
@Accessors(chain = true)
public class RestDTO {
    private Long id;

    private LocalDateTime requestTime;

    private String name;

    private String role;

    private String requestType;

    private String body;

}