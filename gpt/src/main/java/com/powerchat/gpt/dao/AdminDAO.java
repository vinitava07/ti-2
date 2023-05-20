package com.powerchat.gpt.dao;

import com.powerchat.gpt.controller.AdminController.UserLoginRequestData;
import com.powerchat.gpt.model.Admin;
import com.powerchat.gpt.utils.crypto.Encrypt;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

public class AdminDAO extends DAO{

    public AdminDAO(){ super(); }

    public boolean connect(){ return super.connect();}
    public boolean close() {
        return super.close();
    }

    public boolean exists(UserLoginRequestData userLoginRequestData) {
        try {
            Admin admin = getByEmail(userLoginRequestData.email());
            System.out.println(admin.getPassword());
            String encryptedPassword = Encrypt.encrypt(userLoginRequestData.password());
            System.out.println(encryptedPassword);
            return encryptedPassword.equals(admin.getPassword());
          //  return Objects.equals(admin.getPassword(), encryptedPassword);
        } catch(Exception e) {
            return false;
        }
    }
    public boolean insert(Admin admin) {
        boolean status = false;
        try {

            Statement st = connection.createStatement();
            String sql = "INSERT INTO powerchat.admin (id, email, password) "
                    + "VALUES ('" + admin.getId() + "' , '" + admin.getEmail() + "', '" + Encrypt.encrypt(admin.getPassword()) + "');";
            System.out.println(sql);
            st.executeUpdate(sql);
            st.close();

            status = true;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return status;
    }

    public Admin getByEmail(String email) {
        Admin admin = null;
        try {
            Statement st = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            String sql = "SELECT * FROM powerchat.admin WHERE email='" + email +"';";
            System.out.println(sql);
            ResultSet rs = st.executeQuery(sql);
            if (rs.next()) {
                admin = createFrom(rs);
            }
            st.close();
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        return admin;
    }

    public List<Admin> getAll() {
        List<Admin> adms = new ArrayList<Admin>();

        try {
            Statement st = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            String sql = "SELECT * FROM powerchat.admin;";
            System.out.println(sql);
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                Admin admin = createFrom(rs);
                adms.add(admin);
            }
            st.close();
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        return adms;
    }

    private Admin createFrom(ResultSet queryResult) throws Exception {
        UUID id = UUID.fromString(queryResult.getString("id"));
        System.out.println("senha" + queryResult.getString("password"));
        return new Admin(id , queryResult.getString("email"), queryResult.getString("password"));
    }

    public boolean update(Admin admin) {
        boolean status = false;
        try {
            Statement st = connection.createStatement();
            String sql = "UPDATE powerchat.admin SET email = '" + admin.getEmail() + "', password = '"
                    + Encrypt.encrypt(admin.getPassword()) + "' WHERE id = '" + admin.getId() + "';";
            System.out.println(sql);
            st.executeUpdate(sql);
            st.close();
            status = true;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return status;
    }
}
