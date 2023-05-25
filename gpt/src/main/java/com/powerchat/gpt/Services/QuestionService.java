package com.powerchat.gpt.Services;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.powerchat.gpt.dao.QuestionDAO;
import com.powerchat.gpt.model.Question;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;
import java.util.UUID;

@Service
public class QuestionService {

    private StringBuilder json;

    public String getQuestionServiceJson(){
        return json.toString();
    }

    public void storeQuestion(String prompt, String reply, UUID subscriptionID) {
        UUID id = UUID.randomUUID();
        Timestamp createdAt = Timestamp.from(Instant.now());
        Question question = new Question(id, prompt, reply, createdAt, subscriptionID);
        QuestionDAO dao = new QuestionDAO();
        dao.connect();
        dao.insert(question);
        dao.close();
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
