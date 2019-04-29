package cn.tedu.store.service;

import cn.tedu.store.entity.Address;
import cn.tedu.store.service.ex.ServiceException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AddressServiceTestCase {

    @Autowired
    private IAddressService service;

    @Test
    public void addnew() {
        try {
            String username = "超级管理员";
            Address address = new Address();
            address.setUid(3);
            address.setReceiver("小王同学");
            service.addnew(address, username);
            System.err.println("OK.");
        } catch (ServiceException e) {
            System.err.println(e.getMessage());
        }
    }

}








