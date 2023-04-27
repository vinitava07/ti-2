package com.powerchat.gpt.Services;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.powerchat.gpt.model.Plan;
import com.powerchat.gpt.model.Question;

import java.util.List;

public class QuestionService {

    private String json;

    public String getQuestionServiceJson(){
        return json;
    }

    public void parseJson(List<Question> questions) throws Exception{
        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        for (int i = 0; i < questions.size()-1; i++) {
            json += ow.writeValueAsString(questions.get(i)) + ",\n";
        }
        json+=ow.writeValueAsString(questions.get(questions.size()-1));
    }

}
