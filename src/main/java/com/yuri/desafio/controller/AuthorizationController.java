package com.yuri.desafio.controller;

import com.yuri.desafio.dto.authentication.AuthenticationDTO;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class AuthorizationController {
    @ApiOperation("Login.")
    @PostMapping("/login")
    public void fakeLogin(@RequestBody @Valid AuthenticationDTO authenticationDTO) {
        throw new IllegalStateException("This method shouldn't be called. It's implemented by Spring Security filters.");
    }
}
