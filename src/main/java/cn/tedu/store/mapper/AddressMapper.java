package cn.tedu.store.mapper;

import cn.tedu.store.entity.Address;

/**
 * 处理收货地址数据的持久层接口
 */
public interface AddressMapper {

	/**
	 * 插入收货地址数据
	 * @param address 收货地址数据
	 * @return 受影响的行数
	 */
	Integer insert(Address address);

	/**
	 * 根据用户id查询该用户的收货地址的数量
	 * @param uid 用户id
	 * @return 该用户的收货地址的数量，如果该用户当前没有收货地址数据，则返回0
	 */
	Integer getCountByUid(Integer uid);
	
}




