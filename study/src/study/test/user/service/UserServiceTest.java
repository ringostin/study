package study.test.user.service;

import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import study.user.service.UserService;
import study.user.dao.UserDao;
import study.user.domain.Level;
import study.user.domain.User;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="/test-applicationcontext.xml")
public class UserServiceTest {
	@Autowired
	UserService userService;
	
	@Autowired
	private UserDao userDao;
	
	List<User> users;
	
	@Test
	public void bean(){
		assertThat(this.userService, is(notNullValue()));
	}
	
	@Before
	public void setUp() {
		users = Arrays.asList(
				new User("keaton","±æ±âÈ£","1236", Level.BASIC, 49, 0),
				new User("nradix1", "Á¤Áø¼ö", "1234", Level.BASIC, 50, 0),
				new User("wade", "±èµ¿Çõ", "4567", Level.SILVER, 60, 29),
				new User("bumjin", "¹Ú¹üÁø", "4567", Level.SILVER, 60, 30),
				new User("erwins", "±èµ¿Çõ", "4567", Level.GOLD, 100, 100)
				);
	}
	
	@Test
	public void addUser(){
		userDao.deleteAll();
		
		User userWithLevel = users.get(4); //GOLD
		User userWithoutLevel = users.get(0);
		userWithoutLevel.setLevel(null);
		
		
		userService.add(userWithLevel);
		userService.add(userWithoutLevel);
		
		User userWithLevelRead = userDao.get(userWithLevel.getId());
		User userWithoutLevelRead = userDao.get(userWithoutLevel.getId());
		
		assertThat(userWithLevelRead.getLevel(), is(userWithLevel.getLevel()));
		assertThat(userWithoutLevelRead.getLevel(), is(Level.BASIC));
	}
	
	@Test
	public void upgradeLevels(){
		userDao.deleteAll();
		
		for(User user : users){
			userDao.add(user);
		}
		
		userService.upgradeLevels();
		
		checkLevel(users.get(0), Level.BASIC);
		checkLevel(users.get(1), Level.SILVER);
		checkLevel(users.get(2), Level.SILVER);
		checkLevel(users.get(3), Level.GOLD);
		checkLevel(users.get(4), Level.GOLD);
		
	}

	private void checkLevel(User user, Level expectedLevel) {
		User userUpdate = userDao.get(user.getId());
		assertThat(userUpdate.getLevel(), is(expectedLevel));
	}
}
