package cn.tedu.store.service;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import cn.tedu.store.entity.Cart;
import cn.tedu.store.service.ex.ServiceException;
import cn.tedu.store.vo.CartVO;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CartServiceTestCase {
	
	@Autowired
	ICartService service;
	
	@Test
	public void addToCart() {
		try {
			Cart cart = new Cart();
			cart.setUid(10);
			cart.setGid(20L);
			cart.setNum(30);
			String username = "Admin";
			service.addToCart(cart, username);
			System.err.println("OK.");
		} catch (ServiceException e) {
			System.err.println(e.getClass().getName());
			System.err.println(e.getMessage());
		}
	}
	
	@Test
	public void getByUid() {
		Integer uid = 11;
		List<CartVO> list = service.getByUid(uid);
		System.err.println("BEGIN:");
		for (CartVO item : list) {
			System.err.println(item);
		}
		System.err.println("END.");
	}
	
	@Test
	public void addNum() {
		try {
			Integer cid = 3;
			Integer uid = 11;
			String username = "小刘同学";
			Integer newNum = service.addNum(cid, uid, username);
			System.err.println("OK. new num=" + newNum);
		} catch (ServiceException e) {
			System.err.println(e.getClass().getName());
			System.err.println(e.getMessage());
		}
	}
	
}
