package com.geobyte.geobyteinc.controller;

import com.geobyte.geobyteinc.data.dtos.ApiResponse;
import com.geobyte.geobyteinc.data.dtos.request.LoginRequest;
import com.geobyte.geobyteinc.data.dtos.request.RegistrationRequest;
import com.geobyte.geobyteinc.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.ZonedDateTime;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("api/v1/user")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<ApiResponse> register(@RequestBody RegistrationRequest registrationRequest,
                                                HttpServletRequest httpServletRequest) {
        return new ResponseEntity<>(ApiResponse.builder()
                .timeStamp(ZonedDateTime.now())
                .path(httpServletRequest.getRequestURI())
                .statusCode(HttpStatus.CREATED.value())
                .data(userService.register(registrationRequest))
                .isSuccessful(true)
                .build(), HttpStatus.CREATED);
    }
    @PostMapping("/login")
    public ResponseEntity<ApiResponse> login(@RequestBody LoginRequest loginRequest,
                                                HttpServletRequest httpServletRequest) {
        return new ResponseEntity<>(ApiResponse.builder()
                .timeStamp(ZonedDateTime.now())
                .path(httpServletRequest.getRequestURI())
                .statusCode(HttpStatus.OK.value())
                .data(userService.authenticate(loginRequest))
                .isSuccessful(true)
                .build(), HttpStatus.OK);
    }
}
