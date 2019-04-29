package cn.tedu.store.service;

import cn.tedu.store.entity.Address;
import cn.tedu.store.service.ex.InsertException;

/**
 * 处理收货地址数据的业务层接口
 */
public interface IAddressService {

	/**
	 * 新增收货地址
	 *
	 * @param address  收货地址数据
	 * @param username 当前操作执行人
	 * @throws InsertException 插入数据异常
	 */
	void addnew(Address address, String username)
			throws InsertException;
}







