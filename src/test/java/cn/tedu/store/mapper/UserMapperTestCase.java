package cn.tedu.store.mapper;

import cn.tedu.store.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserMapperTestCase {

	@Autowired
	public UserMapper mapper;
	
	@Test
	public void insert() {
		User user = new User();
		user.setUsername("root");
		user.setPassword("1234");
		Integer rows = mapper.insert(user);
		System.err.println("rows=" + rows);
	}
	
	@Test
	public void updatePassword() {
		Integer uid = 9;
		String password = "8888";
		String modifiedUser = "超级管理员";
		Date modifiedTime = new Date();
		Integer rows = mapper.updatePassword(uid, password, modifiedUser, modifiedTime);
		System.err.println("rows=" + rows);
	}
	
	@Test
	public void findByUsername() {
		String username = "admin";
		User result = mapper.findByUsername(username);
		System.err.println(result);
	}
	
	@Test
	public void findByUid() {
		Integer uid = 9;
		User result = mapper.findByUid(uid);
		System.err.println(result);
	}

	@Test
	public void	updateInfo(){
		User user = new User();
		user.setUid(2);
		user.setPhone("12324");
		user.setEmail("123@qq.com");
		user.setGender(1);
		user.setModifiedUser("root");
		user.setModifiedTime(new Date());
		Integer rows=  mapper.updateInfo(user);
		System.err.println(rows);
	}


	@Test
	public void updateAvatar(){
		Integer uid = 3;
		String avatar = "这里应该是头像的路径";
		String modifiedUser ="超级管理员";
		Date modifiedTime = new Date();
		Integer rows = mapper.updateAvatar(uid,avatar,modifiedUser,modifiedTime);
		System.err.println("rows="+rows);
	}

	
}






