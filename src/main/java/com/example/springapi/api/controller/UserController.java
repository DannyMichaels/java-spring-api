package com.example.springapi.api.controller;

import com.example.springapi.service.UserService;
import com.example.springapi.api.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.ResponseEntity;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Optional;

@RestController
public class UserController {

    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users")
    public ResponseEntity<ArrayList<User>> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<Object> getUser(@PathVariable Integer id) {
        Optional<User> user = userService.getUser(id);

        if (user.isPresent()) {
            return ResponseEntity.ok(user.get());
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                new HashMap<String, String>() {{
                    put("error", "User with id " + id + " not found!");
                }});
    }

    @PostMapping(value = "/users", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> createUser(@RequestBody User newUser) throws IOException {
        System.out.println("User: " + newUser);

        User createdUser = userService.createUser( (User) newUser );

        if (createdUser != null) {
            return ResponseEntity.status(HttpStatus.CREATED).body(createdUser);
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                    new HashMap<String, String>() {{
                        put("error", "Failed to create user!");
                    }});
        }
    }

    @GetMapping("/user")
    public ResponseEntity<Object> getUserWithQueryParams(@RequestParam Integer id) {
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
