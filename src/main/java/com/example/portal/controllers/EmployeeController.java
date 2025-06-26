package com.example.portal.controllers;


import com.example.portal.api.Api;
import com.example.portal.api.PositiveResponse;
import com.example.portal.dto.UserCreateDTO;
import com.example.portal.services.ProfileService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

public class EmployeeController {



    @PostMapping(consumes = "multipart/form-data")
    public PositiveResponse<?> createNewUser(@RequestPart("data") @Valid UserCreateDTO body,
                                             @RequestPart("photo") MultipartFile photo) {
        return Api.positiveResponse(profileService.createUser(body, photo));
    }
}
