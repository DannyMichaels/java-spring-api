package com.example.springapi.service;

import com.example.springapi.api.model.User;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Optional;

@Service
public class UserService {
    private ArrayList<User> userList;
    private ObjectMapper objectMapper = new ObjectMapper();

    public UserService() {
        this.userList = new ArrayList<>();
        User user1 = new User(1, "Daniel", 24, "daniel@gmail.com");
        User user2 = new User(2, "Axel", 25, "axel@gmail.com");
        User user3 = new User(3, "Ashe", 26, "ashe@gmail.com");
        User user4 = new User(4, "Jerry", 30, "jerry@gmail.com");
        User user5 = new User(5, "Kurt", 44, "kurt@gmail.com");

        userList.addAll(Arrays.asList(user1, user2, user3,user4,user5));
    }

    public Optional<User> getUser(Integer id) {
        Optional optional = Optional.empty();

        for (User user: userList) {
            if (id == user.getId()) {
                optional = Optional.of(user);
                return optional;
            }
        }

        return optional;
    }

    public ArrayList<User> getAllUsers() {
        return userList;
    }

    public User createUser(User userForm) {
        int newUserId = 0;
        System.out.println("User Form: " + userForm);

        for (User user : userList) {
            if (newUserId == user.getId()) {
                newUserId++;
            }
        }

        User newUser = new User();
        newUser.setId(newUserId);
        newUser.setName(userForm.getName());
        newUser.setEmail(userForm.getEmail());
        newUser.setAge(userForm.getAge());

        userList.add(newUser);

        return newUser;
    }

}
