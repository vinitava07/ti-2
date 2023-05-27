package com.powerchat.gpt.controller;

import com.powerchat.gpt.Services.PlanService;
import com.powerchat.gpt.dao.PlanDAO;
import com.powerchat.gpt.model.Plan;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class PlanController {

    @GetMapping("/plans")
    public ResponseEntity<String> getPlans() throws Exception {
        PlanDAO dao = new PlanDAO();
        dao.connect();
        List<Plan> plans = dao.getAll();
        String plansJson = new PlanService().getAllPlansInJson(plans);
        dao.close();
        return new ResponseEntity<>(plansJson, HttpStatus.OK);
    }
}
