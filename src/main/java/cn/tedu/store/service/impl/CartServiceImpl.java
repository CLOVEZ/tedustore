package cn.tedu.store.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.tedu.store.entity.Cart;
import cn.tedu.store.mapper.CartMapper;
import cn.tedu.store.service.ICartService;
import cn.tedu.store.service.ex.AccessDeniedException;
import cn.tedu.store.service.ex.CartNotFoundException;
import cn.tedu.store.service.ex.InsertException;
import cn.tedu.store.service.ex.UpdateException;
import cn.tedu.store.vo.CartVO;

/**
 * 处理购物车数据的业务层实现类
 */
@Service
public class CartServiceImpl implements ICartService {
	
	@Autowired
	private CartMapper cartMapper;

	@Override
	public void addToCart(Cart cart, String username) throws InsertException, UpdateException {
		// 根据参数cart中的uid和gid执行查询
		Integer uid = cart.getUid();
		Long gid = cart.getGid();
		Cart result = findByUidAndGid(uid, gid);
		Date now = new Date();
		// 判断查询结果是否为null
		if (result == null) {
			// 是：表示当前用户并没有将当前商品添加到购物车，则执行增加
			cart.setCreatedUser(username);
			cart.setCreatedTime(now);
			cart.setModifiedUser(username);
			cart.setModifiedTime(now);
			addnew(cart);
		} else {
			// 否：表示当前用户的购物车中已有该商品，则取出该数据的cid和num
			Integer cid = result.getCid();
			Integer num = result.getNum();
			// 根据参数cart中的num和刚才查询结果中取出的num，计算得到新的num
			Integer newNum = cart.getNum() + num;
			// 执行更新
			updateNum(cid, newNum, username, now);
		}
	}

	@Override
	public List<CartVO> getByUid(Integer uid) {
		return findByUid(uid);
	}

	@Override
	public Integer addNum(Integer cid, Integer uid, String username)
			throws CartNotFoundException, AccessDeniedException, UpdateException {
		// 根据参数cid查询数据
		Cart result = findByCid(cid);
		// 检查查询结果是否为null
		if (result == null) {
			// 是：CartNotFoundException
			throw new CartNotFoundException(
				"增加商品数量失败！尝试访问的购物车数据不存在！");
		}

		// 检查参数uid与查询结果中的uid是否不同
		if (uid != result.getUid()) {
			// 是：AccessDeniedException
			throw new AccessDeniedException(
				"增加商品数量失败！尝试访问的购物车数据归属错误！");
		}

		// 取出查询结果中的商品数量，增加1，得到新的数量
		Integer newNum = result.getNum() + 1;
		// 更新商品数量：updateNum(Integer cid, Integer num, String modifiedUser, Date modifiedTime)
		updateNum(cid, newNum, username, new Date());
		// 返回新的数量
		return newNum;
	}
	
	/**
	 * 增加新的购物车数据
	 * @param cart 购物车数据
	 */
	private void addnew(Cart cart) {
		Integer rows = cartMapper.addnew(cart);
		if (rows != 1) {
			throw new InsertException(
				"将商品添加到购物车失败！插入数据时出现未知错误！");
		}
	}

	/**
	 * 更新购物车中商品的数量
	 * @param cid 购物车数据的id
	 * @param num 商品的新数量
	 * @param modifiedUser 修改执行人
	 * @param modifiedTime 修改时间
	 */
	private void updateNum(Integer cid, Integer num, 
		String modifiedUser, Date modifiedTime) {
		Integer rows = cartMapper.updateNum(cid, num, modifiedUser, modifiedTime);
		if (rows != 1) {
			throw new UpdateException(
				"将商品添加到购物车失败！更新数据时出现未知错误！");
		}
	}

	/**
	 * 根据用户id和商品id查询购物车数据
	 * @param uid 用户id
	 * @param gid 商品id
	 * @return 匹配的购物车数据，如果没有匹配的数据，则返回null
	 */
	private Cart findByUidAndGid(
			Integer uid, Long gid) {
		return cartMapper.findByUidAndGid(uid, gid);
	}
	
	/**
	 * 获取某用户的购物车数据列表
	 * @param uid 用户的id
	 * @return 匹配的购物车数据列表
	 */
	private List<CartVO> findByUid(Integer uid) {
		return cartMapper.findByUid(uid);
	}

	/**
	 * 根据购物车数据的id查询购物车数据
	 * @param cid 购物车数据的id
	 * @return 匹配的购物车数据，如果没有匹配的数据，则返回null
	 */
	private Cart findByCid(Integer cid) {
		return cartMapper.findByCid(cid);
	}

	
}


