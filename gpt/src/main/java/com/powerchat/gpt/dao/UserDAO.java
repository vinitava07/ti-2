package com.powerchat.gpt.dao;

import com.powerchat.gpt.model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDAO extends DAO {

	public UserDAO() {
		super();
	}

	public boolean connect() {
		return super.connect();
	}

	public boolean close() {
		return super.close();
	}

	public boolean insert(User user) {
		boolean status = false;
		try {

			Statement st = connection.createStatement();
			String sql = "INSERT INTO powerchat.user (name, email, phone_number) "
					+ "VALUES ('" + user.getName() + "', '" + user.getEmail() + "', '"
					+ user.getPhoneNumber() + "');";
			System.out.println(sql);
			st.executeUpdate(sql);
			st.close();

			status = true;
		} catch (SQLException u) {
			throw new RuntimeException(u);
		}
		return status;
	}


	public User getById(String phoneNumber) {
		User user = null;
		try {
			Statement st = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			String sql = "SELECT * FROM powerchat.user WHERE phone_number='" + phoneNumber +"';";
			System.out.println(sql);
			ResultSet rs = st.executeQuery(sql);
			if (rs.next()) {
				user = createFrom(rs);
			}
			st.close();
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		return user;
	}

	public List<User> getAll() {
		List<User> users = new ArrayList<User>();

		try {
			Statement st = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			String sql = "SELECT * FROM powerchat.user;";
			System.out.println(sql);
			ResultSet rs = st.executeQuery(sql);
			while (rs.next()) {
				User u = createFrom(rs);
				users.add(u);
			}
			st.close();
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		return users;
	}

	private User createFrom(ResultSet queryResult) throws Exception {
		return new User(queryResult.getString("name"), queryResult.getString("email"), queryResult.getString("phone_number"));
	}

	public boolean update(User user) {
		boolean status = false;
		try {
			Statement st = connection.createStatement();
			String sql = "UPDATE powerchat.user SET email = '" + user.getEmail() + "', name = '"
					+ user.getName() + "'"
					+ " WHERE phone_number = '" + user.getPhoneNumber() + "';";
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
	