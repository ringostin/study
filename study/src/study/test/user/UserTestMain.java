package study.test.user;

import java.sql.SQLException;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

import study.user.dao.DaoFactory;
import study.user.dao.UserDaoJdbc;
import study.user.domain.User;

public class UserTestMain {

	private static ApplicationContext context;

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		
		context = new GenericXmlApplicationContext("applicationContext.xml");
		
		UserDaoJdbc dao = context.getBean("userDao",UserDaoJdbc.class);
		UserDaoJdbc dao2 = context.getBean("userDao",UserDaoJdbc.class);
		
		System.out.println("dao ="+dao);
		System.out.println("dao2 ="+dao);
		
		User user = new User();
		user.setId("nradix");
		user.setName("������");
		user.setPassword("qwer3013");
		
		dao.add(user);
		
		System.out.println(user.getId()+ " ��� ����");
		
		User user2 = dao.get(user.getId());
		
		if(!user.getName().equals(user2.getName())){
			System.out.println("�׽�Ʈ ���� (name)");
			
		}else if(!user.getId().equals(user2.getId())){
			System.out.println("�׽�Ʈ ���� (ID)");
		}else if(!user.getPassword().equals(user2.getPassword())){
			System.out.println("�׽�Ʈ ���� (password)");
		}else{
			System.out.println("�׽�Ʈ ����");
		}
		
	}

}
