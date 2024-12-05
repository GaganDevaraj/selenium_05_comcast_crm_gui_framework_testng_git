package com.comcast.crm.generic.databaseutility;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class DataBaseUtility {

	Connection connection = null;

	public void getConnection(String url, String username, String password) throws Exception {
		Class.forName("com.mysql.cj.jdbc.Driver");
		connection = DriverManager.getConnection(url, username, password);
	}

	public void getConnection() throws Exception {
		String url = "jdbc:mysql://localhost:3306";
		String username = "root";
		String password = "rootroot";
		Class.forName("com.mysql.cj.jdbc.Driver");
		connection = DriverManager.getConnection(url, username, password);
	}

	public void closeConnection(Connection connection) throws Exception {
		try {
			connection.close();
		} catch (Exception e) {
		}
	}

	public ResultSet executeSelectQuery(String quString) throws Exception {
		ResultSet resultSet = null;
		try {
			Statement statement = connection.createStatement();
			resultSet = statement.executeQuery(quString);
		} catch (Exception e) {

		}
		return resultSet;
	}

	public int executeNonSelectQuery(String quString) throws Exception {
		int result = 0;
		try {
			Statement statement = connection.createStatement();
			result = statement.executeUpdate(quString);
		} catch (Exception e) {
		}
		return result;
	}
}
