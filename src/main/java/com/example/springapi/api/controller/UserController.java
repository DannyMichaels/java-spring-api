package com.example.springapi.api.controller;

import com.example.springapi.service.UserService;
import com.example.springapi.api.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;
import java.util.Optional;

@RestController
public class UserController {

    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/user")
    public  ResponseEntity<Object> getUser(@RequestParam Integer id) {
        Optional user = userService.getUser(id);

        if (user.isPresent()) {
            return ResponseEntity.ok(user.get());
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                new HashMap<String, String>() {{
                    put("error", "User with id" + id + " not found!");
                }}
        );
    }
}
