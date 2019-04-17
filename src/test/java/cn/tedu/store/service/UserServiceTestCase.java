package cn.tedu.store.service;

import cn.tedu.store.entity.User;
import cn.tedu.store.service.ex.ServiceException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.DigestUtils;

import java.util.UUID;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceTestCase {

	@Autowired
	public IUserService service;
	
	@Test
	public void reg() {
		try {
			User user = new User();
			user.setUsername("chenxt");
			user.setPassword("1234");
			user.setGender(1);
			user.setPhone("13800138008");
			user.setEmail("upper@tedu.cn");
			user.setAvatar("http://www.tedu.cn/upper.png");
			service.reg(user);
			System.err.println("OK");
		} catch (ServiceException e) {
			System.err.println(e.getClass().getName());
			System.err.println(e.getMessage());
		}
	}
	

	
	@Test
	public void changePassword() {
		try {
			Integer uid = 1;
			String username = "超级管理员";
			String oldPassword = "1234";
			String newPassword = "8888";
			service.changePassword(uid, username, oldPassword, newPassword);;
			System.err.println("OK.");
		} catch (ServiceException e) {
			System.err.println(e.getClass().getName());
			System.err.println(e.getMessage());
		}
	}


	@Test
	public void login() {
		try {
			String username = "root";
			String password = "1234";
			User data = service.login(username, password);
			System.err.println(data);
		} catch (ServiceException e) {
			System.err.println(e.getClass().getName());
			System.err.println(e.getMessage());
		}
	}
	
	@Test
	public void messageDigest() {
		String password = "123456";
		String salt = UUID.randomUUID().toString();
		String result = DigestUtils
				.md5DigestAsHex((salt + password + salt).getBytes());
		System.err.println(result);
	}

	@Test
    public void syso(){
        System.out.println("hello");
    }

	
}










