package com.powerchat.gpt.Services;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.powerchat.gpt.model.Plan;
import com.powerchat.gpt.model.Question;

import java.util.List;

public class QuestionService {

    private StringBuilder json;

    public String getQuestionServiceJson(){
        return json.toString();
    }

    public void parseJson(List<Question> questions) throws Exception{
        this.json = new StringBuilder();

        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        json.append("{\n\"data\":[\n");
        for (int i = 0; i < questions.size() - 1; i++) {
            json.append(ow.writeValueAsString(questions.get(i))).append(",\n");
        }
        json.append(ow.writeValueAsString(questions.get(questions.size() - 1)) + "]\n}");
    }

}
