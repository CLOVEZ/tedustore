package cn.tedu.store.mapper;

import cn.tedu.store.entity.Address;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AddressMapperTestCase {

	@Autowired
	private AddressMapper mapper;

	@Test
	public void insert() {
		Address address = new Address();
		address.setUid(3);
		address.setReceiver("小张同学");
		Integer rows = mapper.insert(address);
		System.err.println("rows=" + rows);
	}

	@Test
	public void getCountByUid() {
		Integer uid = 3;
		Integer count = mapper.getCountByUid(uid);
		System.err.println("count=" + count);
	}

}









