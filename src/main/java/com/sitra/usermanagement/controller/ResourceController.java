package com.sitra.usermanagement.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class ResourceController {

    @GetMapping("/protected-resource")
    @PreAuthorize("hasAuthority('SCOPE_read')") // Require the 'read' scope
    public String getProtectedResource() {
        return "This is a protected resource that requires an access token with the 'read' scope.";
    }

    @GetMapping("/write-resource")
    @PreAuthorize("hasAuthority('SCOPE_write')") // Require the 'write' scope
    public String getWriteResource() {
        return "This is a protected resource that requires an access token with the 'write' scope.";
    }
}

