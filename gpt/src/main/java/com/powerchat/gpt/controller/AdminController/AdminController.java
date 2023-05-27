package com.powerchat.gpt.controller.AdminController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.powerchat.gpt.Services.AdminService;
import com.powerchat.gpt.dao.AdminDAO;
import com.powerchat.gpt.model.Admin;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class AdminController {

    @GetMapping("/admin")
    public ResponseEntity<String> handleHealthCheck() throws Exception {
        try {
            AdminDAO adminDAO = new AdminDAO();
            adminDAO.connect();
            List<Admin> admins = adminDAO.getAll();
            adminDAO.close();
            String json = new AdminService(admins).getJson();
            return new ResponseEntity<>(json, HttpStatus.OK);
        } catch(Exception e) {
            return new ResponseEntity<>("Unable to get admins Json", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/login")
    public ResponseEntity<String> handleLogin(@RequestBody String payload) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        UserLoginRequestData userLoginRequestData = mapper.readValue(payload, UserLoginRequestData.class);
        AdminDAO adminDAO = new AdminDAO();
        System.out.println(userLoginRequestData.password());
        adminDAO.connect();
        boolean result = adminDAO.exists(userLoginRequestData);
        adminDAO.close();
        if (result) {
            return new ResponseEntity<>("OK", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("User is not authorized", HttpStatus.FORBIDDEN);
        }
    }
}

