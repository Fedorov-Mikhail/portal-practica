package com.example.portal.dto;

import com.example.portal.entities.UserRole;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import jakarta.persistence.Transient;
import java.time.LocalDate;

@Data
public class UserDTO {
    @NotBlank
    private String name;
    private LocalDate birthday;
    private LocalDate startWork;
    private String telegram;
    @NotBlank
    private String city;
    @Email
    @NotBlank
    private String email;
    @NotBlank
    private String phoneNumber;
    @NotBlank
    private String login;

    @Transient
    private String password;

    @NotNull
    private UserRole role;

    private String photo;

}