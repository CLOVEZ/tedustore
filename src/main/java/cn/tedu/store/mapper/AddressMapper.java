package cn.tedu.store.mapper;

import cn.tedu.store.entity.Address;

/**
 * 处理用户数据的持久层接口
 */
public interface AddressMapper {

	/**
	 * 增加收货地址数据
	 * @param address 收货数据
	 * @return 受影响的行数
	 */
	Integer insert(Address address);

	/**
	 * 统计某用户的收货地址的数量
	 * @param uid 用户的id
	 * @return 收货地址的数量
	 */
	Integer countByUid(Integer uid);

}





