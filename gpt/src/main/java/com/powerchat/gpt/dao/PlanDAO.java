package com.powerchat.gpt.dao;

import com.powerchat.gpt.model.Plan;
import com.powerchat.gpt.model.Question;
import com.powerchat.gpt.model.Subscription;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class PlanDAO extends DAO {

    public PlanDAO() {
        super();
    }

    public boolean connect() {
        return super.connect();
    }

    public boolean close() {
        return super.close();
    }

    public boolean insert(Plan plan) {
        boolean status = false;
        try {
            Statement st = connection.createStatement();
            String sql = "INSERT INTO powerchat.plan (id, name, monthly_prompt_limit) "
                    + "VALUES (" + plan.id + ", '" + plan.name + "', '"
                    + plan.monthlyPromptLimit + "');";
            System.out.println(sql);
            st.executeUpdate(sql);
            st.close();
            status = true;
        } catch (SQLException u) {
            throw new RuntimeException(u);
        }
        return status;
    }


    public Plan getById(String id) {
        Plan plan = null;
        try {
            Statement st = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            String sql = "SELECT * FROM powerchat.plan WHERE id='" +id + "';";
            System.out.println(sql);
            ResultSet rs = st.executeQuery(sql);
            if (rs.next()) {
                plan = createFrom(rs);
            }
            st.close();
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        return plan;
    }

    public List<Plan> getAll() {
        List<Plan> plan = new ArrayList<Plan>();

        try {
            Statement st = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            String sql = "SELECT * FROM powerchat.plan;";
            System.out.println(sql);
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                Plan q = createFrom(rs);
                plan.add(q);
            }
            st.close();
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        return plan;
    }

    private Plan createFrom(ResultSet queryResult) throws Exception {
        //UUID id = UUID.fromString(queryResult.getString("id"));
        return new Plan(queryResult.getString("id"), queryResult.getString("name"), queryResult.getInt("monthly_prompt_limit"));
    }

    public boolean update(Plan plan) {
        boolean status = false;
        try {
            Statement st = connection.createStatement();
            String sql = "UPDATE powerchat.plan SET name = '" + plan.name + "', monthly_prompt_limit = '"
                    + plan.monthlyPromptLimit + "'"
                    + " WHERE id = '" + plan.id + "';";
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