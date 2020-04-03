package br.com.kickpost.tempmute.storage;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class MySQL {

	private String user;
	private String pass;
	private String db;
	private String host;
	public Connection connection;

	public MySQL(String user, String pass, String host, String db) {
		this.user = user;
		this.pass = pass;
		this.db = db;
		this.host = host;
		connect();
	}

	public void close() {
		try {
			if (connection != null) {
				connection.close();
			}
		} catch (Exception ex) {
		}
	}

	public void connect() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			this.connection = DriverManager
					.getConnection("jdbc:mysql://" + host + ":3306/" + db + "?autoReconnect=true", user, pass);

		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	public void disconnect() {
		try {
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public PreparedStatement getPreparedStatement(String query) {
		try {
			return this.connection.prepareStatement(query);
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
}
