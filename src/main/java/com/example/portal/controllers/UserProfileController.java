package com.example.portal.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.portal.entities.User;
import com.example.portal.services.ProfileService;
import org.springframework.web.bind.annotation.PathVariable;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserProfileController {


    @Autowired
    private final ProfileService profileService;

    public UserProfileController(ProfileService profileService) {
        this.profileService = profileService;
    }

    @GetMapping("/profile/{id}")
    public User getProfile(@PathVariable Long id) {
        return profileService.getUserProfileById(id);
    }

    @GetMapping("/profile/all")
    public List<User> getAllUsers() {
       return profileService.getAllUserProfiles();
    }
}

