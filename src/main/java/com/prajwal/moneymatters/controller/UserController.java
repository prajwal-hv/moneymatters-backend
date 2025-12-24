package com.prajwal.moneymatters.controller;

import com.prajwal.moneymatters.dto.UserRegisterRequest;
import com.prajwal.moneymatters.dto.UserResponse;
import com.prajwal.moneymatters.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public UserResponse register(@Valid @RequestBody UserRegisterRequest request) {
        return userService.register(request);
    }
}
