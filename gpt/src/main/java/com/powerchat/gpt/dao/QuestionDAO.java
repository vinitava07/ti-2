package com.powerchat.gpt.dao;

import com.powerchat.gpt.model.Question;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class QuestionDAO extends DAO {

    public QuestionDAO() {
        super();
    }

    public boolean connect() {
        return super.connect();
    }

    public boolean close() {
        return super.close();
    }

    public boolean insert(Question question) {
        boolean status = false;
        try {
            Statement st = connection.createStatement();
            String sql = "INSERT INTO powerchat.question (question_subscription, question, reply, created_at, id) "
                    + "VALUES ('" + question.getSubscriptionId() + "', '" + question.getQuestion() + "', '"
                    + question.getReply() + "', '" + question.getCreatedAt() + "', '" + question.getId() + "');";
            System.out.println(sql);
            st.executeUpdate(sql);
            st.close();
            status = true;
        } catch (SQLException u) {
            throw new RuntimeException(u);
        }
        return status;
    }


    public Question getById(UUID id) {
        Question question = null;
        try {
            Statement st = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            String sql = "SELECT * FROM powerchat.question WHERE id='" + id + "';";
            System.out.println(sql);
            ResultSet rs = st.executeQuery(sql);
            if (rs.next()) {
                question = createFrom(rs);
            }
            st.close();
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        return question;
    }

    public List<Question> getAll() {
        List<Question> questions = new ArrayList<Question>();

        try {
            Statement st = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            String sql = "SELECT * FROM powerchat.question;";
            System.out.println(sql);
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                Question q = createFrom(rs);
                questions.add(q);
            }
            st.close();
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        return questions;
    }

    private Question createFrom(ResultSet queryResult) throws Exception {
        UUID id = UUID.fromString(queryResult.getString("id"));
        UUID subscriptionId = UUID.fromString(queryResult.getString("id"));
        return new Question(id, queryResult.getString("question"), queryResult.getString("reply"), queryResult.getTimestamp("created_at"),subscriptionId);
    }

    public boolean update(Question question) {
        boolean status = false;
        try {
            Statement st = connection.createStatement();
            String sql = "UPDATE powerchat.question SET question = '" + question.getQuestion() + "', reply = '"
                    + question.getReply() + "'"
                    + " WHERE id = '" + question.getId() + "';";
            System.out.println(sql);
            st.executeUpdate(sql);
            st.close();
            status = true;
        } catch (SQLException u) {
            throw new RuntimeException(u);
        }
        return status;
    }
}