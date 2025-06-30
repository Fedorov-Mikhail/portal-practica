package com.example.portal.controllers;

import com.example.portal.api.Api;
import com.example.portal.api.PositiveResponse;
import com.example.portal.services.RestAuditService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("rest-audit")
@RequiredArgsConstructor
public class AuditController {
    private final RestAuditService restAuditService;
    @GetMapping
    public PositiveResponse<?> getRestRequests() {
        return Api.positiveResponse(restAuditService.getAllRequests());
    }
}
