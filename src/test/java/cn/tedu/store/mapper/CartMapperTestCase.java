package cn.tedu.store.mapper;

import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import cn.tedu.store.entity.Cart;
import cn.tedu.store.vo.CartVO;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CartMapperTestCase {
	
	@Autowired
	CartMapper mapper;
	
	@Test
	public void addnew() {
		Cart cart = new Cart();
		cart.setUid(1);
		cart.setGid(2L);
		cart.setNum(3);
		Integer rows = mapper.addnew(cart);
		System.err.println("rows=" + rows);
	}
	
	@Test
	public void updateNum() {
		Integer cid = 1;
		Integer num = 50;
		String modifiedUser = "系统管理员";
		Date modifiedTime = new Date();
		Integer rows = mapper.updateNum(cid, num, modifiedUser, modifiedTime);
		System.err.println("rows=" + rows);
	}
	
	@Test
	public void findByUidAndGid() {
		Integer uid = 1;
		Long gid = 2L;
		Cart cart = mapper.findByUidAndGid(uid, gid);
		System.err.println(cart);
	}
	
	@Test
	public void findByUid() {
		Integer uid = 11;
		List<CartVO> list = mapper.findByUid(uid);
		System.err.println("BEGIN:");
		for (CartVO item : list) {
			System.err.println(item);
		}
		System.err.println("END.");
	}

	@Test
	public void findByCid() {
		Integer cid = 5;
		Cart cart = mapper.findByCid(cid);
		System.err.println(cart);
	}
}





