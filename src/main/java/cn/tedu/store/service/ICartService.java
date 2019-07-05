package cn.tedu.store.service;

import java.util.List;

import cn.tedu.store.entity.Cart;
import cn.tedu.store.service.ex.AccessDeniedException;
import cn.tedu.store.service.ex.CartNotFoundException;
import cn.tedu.store.service.ex.InsertException;
import cn.tedu.store.service.ex.UpdateException;
import cn.tedu.store.vo.CartVO;

/**
 * 处理购物车数据的业务层接口
 */
public interface ICartService {

	/**
	 * 将商品添加到购物车
	 * @param cart 购物车数据
	 * @param username 当前执行操作的用户的用户名
	 * @throws InsertException
	 * @throws UpdateException
	 */
	void addToCart(Cart cart, String username) 
			throws InsertException, UpdateException;
	
	/**
	 * 获取某用户的购物车数据列表
	 * @param uid 用户的id
	 * @return 匹配的购物车数据列表
	 */
	List<CartVO> getByUid(Integer uid);
	
	/**
	 * 增加购物车中商品的数量
	 * @param cid 购物车数据的id
	 * @param uid 当前登录的用户的id
	 * @param username 当前登录的用户的用户名
	 * @return 增加成功后的新的数量
	 * @throws CartNotFoundException
	 * @throws AccessDeniedException
	 * @throws UpdateException
	 */
	Integer addNum(Integer cid, 
		Integer uid, String username) 
			throws CartNotFoundException, 
				AccessDeniedException, UpdateException;
	
}






