package com.powerchat.gpt.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.powerchat.gpt.Services.PlanService;
import com.powerchat.gpt.dao.PlanDAO;
import com.powerchat.gpt.dao.SubscriptionDAO;
import com.powerchat.gpt.model.Plan;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

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

    @PostMapping("/update_plan")
    public ResponseEntity<String> updatePlan(@RequestBody String payload) throws Exception{
        ObjectMapper objectMapper = new ObjectMapper();
        UpdateRequestData updateRequestData = objectMapper.readValue(payload, UpdateRequestData.class);
        PlanDAO dao = new PlanDAO();
        System.out.println(updateRequestData.id() + " - " + updateRequestData.monthly_prompt_limit());
        dao.connect();
        Plan plan = dao.getById(updateRequestData.id());
        plan.monthlyPromptLimit = updateRequestData.monthly_prompt_limit();
        boolean updated = dao.update(plan);
        dao.close();
        if(updated){
            return new ResponseEntity<>("OK", HttpStatus.OK);
        }else{
            return new ResponseEntity<>("Failed to update plan", HttpStatus.NOT_MODIFIED);
        }
    }

    @PostMapping("/create_plan")
    public ResponseEntity<String> createPlan(@RequestBody String payload) throws Exception{
        ObjectMapper objectMapper = new ObjectMapper();
        CreateRequestData createRequestData = objectMapper.readValue(payload, CreateRequestData.class);
        Plan plan = new Plan(createRequestData.id(), createRequestData.name(), createRequestData.monthly_prompt_limit());
        System.out.println(plan.id + " - " + plan.name + " - " + plan.monthlyPromptLimit);
        PlanDAO dao = new PlanDAO();
        dao.connect();
        boolean created = dao.insert(plan);
        dao.close();
        if(created){
            return new ResponseEntity<>("OK", HttpStatus.OK);
        }else{
            return new ResponseEntity<>("Failed to create new plan!", HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/plans/{id}")
    public ResponseEntity<String> deletePlan(@PathVariable("id") String id) throws Exception {
        PlanDAO dao = new PlanDAO();
        dao.connect();
        boolean deleted = dao.deleteByPlanId(id);
        dao.close();
        if(deleted){
            return new ResponseEntity<>("DELETED", HttpStatus.OK);
        }else{
            return new ResponseEntity<>("Failed to delete plan!", HttpStatus.NOT_MODIFIED);
        }
    }
}

record CreateRequestData(String id, String name, int monthly_prompt_limit) {}
record UpdateRequestData(String id, int monthly_prompt_limit) {}