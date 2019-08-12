package cn.tedu.store.service;

import java.util.List;

import cn.tedu.store.entity.Order;
import cn.tedu.store.entity.OrderItem;

/**
 * 处理订单数据和订单商品数据的业务层接口
 */
public interface IOrderService {
	
	public static interface Status {
		int UNPAID = 0;
		int PAID = 1;
		int CANCELED = 2;
		int CLOSED = 3;
	}

	/**
	 * 创建订单
	 * @param aid 收货地址id
	 * @param cids 将购买的商品的购物车数据id
	 * @param uid 用户id
	 * @param username 用户名
	 * @return 成功创建的订单对象
	 */
	Order create(Integer aid, Integer[] cids, Integer uid, String username);
	
	/**
	 * 修改订单状态
	 * @param oid 订单id
	 * @param status 新的状态值，应该使用 {@link Status} 中的静态常量
	 * @param username 操作执行人
	 * @see Status
	 */
	void changeStatus(Integer oid, Integer status, String username);
	
	/**
	 * 关闭订单
	 * @param oid 需要关闭的订单id
	 * @param orderItems 需要关闭的订单中的商品列表
	 * @param username 修改执行人
	 */
	void close(Integer oid, List<OrderItem> orderItems, String username);
	
}
