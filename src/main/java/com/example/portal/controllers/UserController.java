package com.example.portal.controllers;

import com.example.portal.api.Api;
import com.example.portal.api.PositiveResponse;
import com.example.portal.dto.UserCreateDTO;
import com.example.portal.dto.UserUpdateDTO;
import com.example.portal.services.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import com.example.portal.entities.User;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    @SneakyThrows
    @GetMapping("{id}")
    public PositiveResponse<?> getUser(@PathVariable Long id) {
        return Api.positiveResponse(userService.getUser(id));
    }

    @GetMapping("birthdays")
    public PositiveResponse<?> getUpcomingBirthdays() {
        return Api.positiveResponse(userService.getBirthdays());
    }

    @GetMapping()
    public PositiveResponse<?> getAllUsers() {
        return Api.positiveResponse(userService.getAllUsers());
    }

    @PostMapping(consumes = "multipart/form-data")
    public PositiveResponse<?> createNewUser(@RequestPart(value = "name") String name,
                                             @RequestPart(value = "birthday") String birthday,
                                             @RequestPart(value = "startWork") String startWork,
                                             @RequestPart(value = "telegram") String telegram,
                                             @RequestPart(value = "city") String city,
                                             @RequestPart(value = "email") String email,
                                             @RequestPart(value = "phoneNumber") String phoneNumber,
                                             @RequestPart(value = "login") String login,
                                             @RequestPart(value = "password") String password,
                                             @RequestPart(value = "role") String role,
                                             @RequestPart(value = "photo") MultipartFile photo) {
        return Api.positiveResponse(userService.createUser(name, birthday, startWork, telegram, city, email, phoneNumber, login, password, role, photo));
    }

    @PostMapping(value = "{id}", consumes = "multipart/form-data")
    public PositiveResponse<?> updateUserPhoto(@RequestPart("data") @PathVariable @Valid Long id,
                                               @RequestPart(value = "photo", required = false) MultipartFile photo) {
        return Api.positiveResponse(userService.updateUserPhoto(id, photo));
    }

    @PostMapping("{id}")
    public PositiveResponse<?> updateUser(@PathVariable Long id, @RequestBody @Valid UserUpdateDTO body) {
        return Api.positiveResponse(userService.updateUser(id, body));
    }

    @DeleteMapping("{id}")
    public PositiveResponse<?> deleteUser(@PathVariable Long id) {
        return Api.positiveResponse(userService.deleteUser(id));
    }
}
