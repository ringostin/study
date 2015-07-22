package study.test.user;

import java.sql.SQLException;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import study.user.dao.CountingConnectionMaker;
import study.user.dao.CountingDaoFactory;
import study.user.dao.UserDaoJdbc;
import study.user.domain.User;

public class UserDaoConnectionCountingTest {
	private static ApplicationContext context;

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		
		context = new AnnotationConfigApplicationContext(CountingDaoFactory.class);
		
		UserDaoJdbc dao = context.getBean("userDao",UserDaoJdbc.class);
		
		CountingConnectionMaker ccm = context.getBean("connectionMaker",CountingConnectionMaker.class);
		
		System.out.println("Connection counter : "+ ccm.getCounter());
		
	}

}
