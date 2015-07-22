package study.user.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.dao.DuplicateKeyException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;



import study.user.domain.Level;
import study.user.domain.User;

public class UserDaoJdbc implements UserDao{
	private JdbcTemplate jdbcTemplate;
	
	private RowMapper<User> userMapper = 
			new RowMapper<User>(){
				public User mapRow(ResultSet rs, int rowNum) throws SQLException{
					User user = new User();
					user.setId(rs.getString("id"));
					user.setName(rs.getString("name"));
					user.setPassword(rs.getString("password"));
					user.setLevel(Level.valueOf(rs.getInt("level")));
					user.setLogin(rs.getInt("login"));
					user.setRecommend(rs.getInt("recommend"));
					return user;
				}
			};
	public void setDataSource(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	public void add(final User user) throws DuplicateKeyException {
		this.jdbcTemplate.update("insert into spring_users(id, name, password, level, login, recommend) values (?,?,?,?,?,?)",
				user.getId(),user.getName(),user.getPassword(),user.getLevel().intValue(), user.getLogin(), user.getRecommend());
			
	}

	
	public User get(String id) {
		return this.jdbcTemplate.queryForObject("select * from spring_users where id = ?", 
				new Object[] {id}, 
				userMapper
		);
	}
	
	public void deleteAll() {
		
		this.jdbcTemplate.update("delete from spring_users");
				
	}
	
	
	public int getCount() {
		return this.jdbcTemplate.queryForInt("select count(*) from spring_users");
	}

	public List<User> getAll() {
		// TODO Auto-generated method stub
		return this.jdbcTemplate.query("select * from spring_users order by id",
				userMapper);
	}

	@Override
	public int update(User user) {
		return this.jdbcTemplate.update("update spring_users set name =?, password = ?, level = ?, login = ?, recommend = ? where id = ?",
				user.getName(),user.getPassword(),user.getLevel().intValue(), user.getLogin(), user.getRecommend(),user.getId());
		
	}

	public void setConnectionMaker(ConnectionMaker connectionMaker) {
		// TODO Auto-generated method stub
		
	}

}
