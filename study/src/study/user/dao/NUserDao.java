package study.user.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import study.user.domain.User;

public class NUserDao extends UserDaoJdbc {
	
	public NUserDao(ConnectionMaker connectionMaker) {
		super.setConnectionMaker(connectionMaker);
		// TODO Auto-generated constructor stub
	}

	public Connection getConnection() throws ClassNotFoundException,
		SQLException {
		Class.forName("com.mysql.jdbc.Driver");
		Connection c = DriverManager.getConnection("jdbc:mysql://localhost/spring_db","spring","spring1234");
		return c;
	}

}
