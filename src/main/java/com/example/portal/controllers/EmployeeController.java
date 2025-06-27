package com.example.portal.controllers;


import com.example.portal.api.Api;
import com.example.portal.api.PositiveResponse;
import com.example.portal.dto.UserCreateDTO;
import com.example.portal.services.ProfileService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("employee")
public class EmployeeController {
    private final ProfileService profileService;

    public EmployeeController(ProfileService profileService) {
        this.profileService = profileService;
    }


    @PostMapping(value = "create", consumes = "multipart/form-data")
    public PositiveResponse<?> createNewUser(@RequestPart("data") @Valid UserCreateDTO body,
                                             @RequestPart("photo") MultipartFile photo) {
        return Api.positiveResponse(profileService.createUser(body, photo));
    }
    @DeleteMapping("{id}")
    public PositiveResponse<?> deleteUser(@PathVariable Long id) {
        return Api.positiveResponse(profileService.deleteUser(id));
    }
}
