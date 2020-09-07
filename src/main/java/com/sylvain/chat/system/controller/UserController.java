package com.sylvain.chat.system.controller;

import com.sylvain.chat.system.DTO.UserRegisterDTO;
import com.sylvain.chat.system.exception.ErrorCode;
import com.sylvain.chat.system.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor(onConstructor = @_(@Autowired))
@RequestMapping("/api/user")
public class UserController {
    private final UserService userService;

    @GetMapping("/hello")
    public String hello(){
        return "Hello World!";
    }

    @PostMapping("/register")
    public ResponseEntity<Void> register(@RequestBody @Valid UserRegisterDTO userRegisterDTO){
        userService.save(userRegisterDTO);
        return ResponseEntity.ok().build();
    }

    /**
     * just for test role access
     * @return
     */
    @GetMapping("/test/all")
    @PreAuthorize("hasAnyRole('ROLE_USER','ROLE_ADMIN')")
    public String testUser(){
        return "Hello! This resource is open to user and admin";
    }

    /**
     * just for test role access
     * @return
     */
    @GetMapping("/test/admin")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    public String testAdmin(){
        return "Hello! This resource is open to admin";
    }
}
