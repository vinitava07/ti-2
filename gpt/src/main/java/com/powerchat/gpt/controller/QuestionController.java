package com.powerchat.gpt.controller;

import com.powerchat.gpt.Services.QuestionService;
import com.powerchat.gpt.dao.QuestionDAO;
import com.powerchat.gpt.model.Question;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class QuestionController {

    @GetMapping("/question")
    public ResponseEntity<String> handleHealthCheck() throws Exception {
        QuestionDAO questionDAO = new QuestionDAO();
        List<Question> questionList = questionDAO.getAll();
        QuestionService questionService = new QuestionService();
        questionService.parseJson(questionList);
        String json = questionService.getQuestionServiceJson();
        return new ResponseEntity<>("OK", HttpStatus.OK);
    }
}