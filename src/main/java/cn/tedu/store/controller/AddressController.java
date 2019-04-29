package cn.tedu.store.controller;

import cn.tedu.store.entity.Address;
import cn.tedu.store.service.IAddressService;
import cn.tedu.store.util.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("addresses")
public class AddressController
        extends BaseController {

    @Autowired
    private IAddressService addressService;

    @PostMapping("addnew")
    public ResponseResult<Void> addnew(
            Address address, HttpSession session) {
        // 从session中获取username
        String username = session.getAttribute("username").toString();
        // 从session中获取uid
        Integer uid = getUidFromSession(session);
        // 将uid封装到address中
        address.setUid(uid);
        // 直接调用业务层对象的addnew(address, username);
        addressService.addnew(address, username);
        // 返回
        return new ResponseResult<Void>(SUCCESS);
    }

}





