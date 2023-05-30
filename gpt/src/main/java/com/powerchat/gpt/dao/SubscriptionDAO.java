package com.powerchat.gpt.dao;

import com.powerchat.gpt.model.Question;
import com.powerchat.gpt.model.Subscription;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class SubscriptionDAO extends DAO {

    public SubscriptionDAO() {
        super();
    }

    public boolean connect() {
        return super.connect();
    }

    public boolean close() {
        return super.close();
    }

    public boolean insert(Subscription subscription) {
        boolean status = false;
        try {
            Statement st = connection.createStatement();
            String sql = "INSERT INTO powerchat.subscription (id, subscription_user, plan, created_at, expiration_date, is_active) "
                    + "VALUES ('" + subscription.id + "', '" + subscription.userID + "', '"
                    + subscription.planID + "', '" + subscription.createdAt + "', '" + subscription.expirationDate + "', " +
                     subscription.isActive + ");";
            System.out.println(sql);
            st.executeUpdate(sql);
            st.close();
            status = true;
        } catch (SQLException u) {
            throw new RuntimeException(u);
        }
        return status;
    }


    public Subscription getById(UUID id) {
        Subscription subscription = null;
        try {
            Statement st = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            String sql = "SELECT * FROM powerchat.subscription WHERE id='" + id +"';";
            System.out.println(sql);
            ResultSet rs = st.executeQuery(sql);
            if (rs.next()) {
                subscription = createFrom(rs);
            }
            st.close();
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        return subscription;
    }
    public boolean deleteByUserId(UUID id) {
        boolean isDeleted = false;
        try {
            Statement st = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            String sql = "DELETE FROM powerchat.question WHERE question_subscription ='"+id +"';\nDELETE FROM powerchat.subscription WHERE id='" + id +"';";
            System.out.println(sql);
            ResultSet rs = st.executeQuery(sql);
            st.close();
            isDeleted = true;
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        return isDeleted;

    }


    public Subscription getByUserId(String phoneNumber) {
        Subscription subscription = null;
        try {
            Statement st = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            String sql = "SELECT * FROM powerchat.subscription WHERE subscription_user ='" + phoneNumber +"';";
            System.out.println(sql);
            ResultSet rs = st.executeQuery(sql);
            if (rs.next()) {
                subscription = createFrom(rs);
            }
            st.close();
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        return subscription;
    }

    public List<Subscription> getAll() {
        List<Subscription> subscription = new ArrayList<Subscription>();

        try {
            Statement st = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            String sql = "SELECT * FROM powerchat.subscription;";
            System.out.println(sql);
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                Subscription q = createFrom(rs);
                subscription.add(q);
            }
            st.close();
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        return subscription;
    }

    private Subscription createFrom(ResultSet queryResult) throws Exception {
        UUID id = UUID.fromString(queryResult.getString("id"));
        return new Subscription(id, queryResult.getString("subscription_user"), queryResult.getString("plan"), queryResult.getTimestamp("created_at"),
                 queryResult.getBoolean("is_active"), queryResult.getTimestamp("expiration_date"));
    }


    public boolean update(Subscription subscription) {
        boolean status = false;
        try {
            Statement st = connection.createStatement();
            String sql = "UPDATE powerchat.subscription SET subscription_user = '" + subscription.userID + "', plan = '"
                    + subscription.planID + "', created_at = '" + subscription.createdAt + "', expiration_date = '" + subscription.expirationDate + "', is_active = "
                    + subscription.isActive + " WHERE id = '" + subscription.id + "';";
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