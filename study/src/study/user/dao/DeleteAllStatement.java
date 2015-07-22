package study.user.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DeleteAllStatement implements StatementStrategy {

	@Override
	public PreparedStatement makeStatement(Connection c) throws SQLException {
		PreparedStatement ps;
		ps = c.prepareStatement("delete from spring_users");
		return ps;
	}

	@Override
	public PreparedStatement makePreparedStatement(Connection c)
			throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

}
