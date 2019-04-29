package cn.tedu.store.service.impl;

import cn.tedu.store.entity.Address;
import cn.tedu.store.mapper.AddressMapper;
import cn.tedu.store.service.IAddressService;
import cn.tedu.store.service.ex.InsertException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * 收货地址的业务层实现类
 */
@Service
public class AddressServiceImpl
		implements IAddressService {

	@Autowired
	private AddressMapper addressMapper;

	@Override
	public void addnew(Address address, String username)
			throws InsertException {
		// TODO 确定district的值

		// 获取当前用户的收货地址数量：getCountByUid(address.getUid());
		Integer count = getCountByUid(address.getUid());
		// 如果数量为0，则isDefault为1，否则，isDefault为0
		address.setIsDefault(count == 0 ? 1 : 0);

		// 4项日志
		Date now = new Date();
		address.setCreatedUser(username);
		address.setCreatedTime(now);
		address.setModifiedUser(username);
		address.setModifiedTime(now);

		// 执行插入数据：insert(address)
		insert(address);
	}

	/**
	 * 插入收货地址数据
	 *
	 * @param address 收货地址数据
	 * @return 受影响的行数
	 */
	private void insert(Address address) {
		Integer rows = addressMapper.insert(address);
		if (rows != 1) {
			throw new InsertException(
					"新增收货地址数据时出现未知错误！");
		}
	}

	/**
	 * 根据用户id查询该用户的收货地址的数量
	 *
	 * @param uid 用户id
	 * @return 该用户的收货地址的数量，如果该用户当前没有收货地址数据，则返回0
	 */
	private Integer getCountByUid(Integer uid) {
		return addressMapper.getCountByUid(uid);
	}

}
