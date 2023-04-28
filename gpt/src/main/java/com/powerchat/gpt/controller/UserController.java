package com.powerchat.gpt.controller;

import com.powerchat.gpt.Services.UserService;
import com.powerchat.gpt.dao.UserDAO;
import com.powerchat.gpt.model.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserController {

    @GetMapping("/user")
    public ResponseEntity<String> handleHealthCheck() throws Exception {
        try {
            UserDAO userDAO = new UserDAO();
            List<User> users = userDAO.getAll();
            String json = new UserService(users).getJSON();
            return new ResponseEntity<>(json, HttpStatus.OK);
        } catch(Exception e) {
            return new ResponseEntity<>("Unable to get users Json", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}