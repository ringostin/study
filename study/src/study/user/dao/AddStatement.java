package study.user.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import study.user.domain.User;

public class AddStatement implements StatementStrategy {
	User user;
	
	public AddStatement(User user){
		this.user =user;
	}
	
	@Override
	public PreparedStatement makeStatement(Connection c) throws SQLException {
		PreparedStatement ps = c.prepareStatement("insert into spring_users(id, name, password) values (?,?,?)");
		ps.setString(1, user.getId());
		ps.setString(2, user.getName());
		ps.setString(3, user.getPassword());
		
		return ps;
	}

	@Override
	public PreparedStatement makePreparedStatement(Connection c)
			throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

}
