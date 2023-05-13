package com.powerchat.gpt.controller;

import com.powerchat.gpt.Services.AdminService;
import com.powerchat.gpt.dao.AdminDAO;
import com.powerchat.gpt.model.Admin;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class AdminController {

    @GetMapping("/admin")
    public ResponseEntity<String> handleHealthCheck() throws Exception {
        try {
            AdminDAO adminDAO = new AdminDAO();
            adminDAO.connect();
            List<Admin> admins = adminDAO.getAll();
            String json = new AdminService(admins).getJson();
            return new ResponseEntity<>(json, HttpStatus.OK);
        } catch(Exception e) {
            return new ResponseEntity<>("Unable to get admins Json", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}