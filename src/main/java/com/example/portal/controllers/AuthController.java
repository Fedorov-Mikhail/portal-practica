package com.example.portal.controllers;

import com.example.portal.api.Api;
import com.example.portal.api.PositiveResponse;
import com.example.portal.dto.AuthDTO;
import com.example.portal.services.AuthService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @PostMapping("login")
    public PositiveResponse<?> auth(@RequestBody @Valid AuthDTO body, HttpServletRequest request) {
        return Api.positiveResponse(authService.login(body, request));
    }

    @GetMapping("refresh-token")
    public PositiveResponse<?> refreshToken(HttpServletRequest request) {
        return Api.positiveResponse(authService.refreshToken(request));
    }

    @PostMapping("logout")
    public PositiveResponse<?> logout(HttpServletRequest request) {
        return Api.positiveResponse(authService.logout(request));
    }
}