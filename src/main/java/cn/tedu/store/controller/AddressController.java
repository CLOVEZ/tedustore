package cn.tedu.store.controller;


import cn.tedu.store.entity.Address;
import cn.tedu.store.service.IAddressService;
import cn.tedu.store.util.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
@RequestMapping("address")
public class AddressController extends BaseController{
    @Autowired
    private IAddressService addressService;

    @RequestMapping("addnew")
    public ResponseResult<Void> addnew(Address address , HttpSession session){
        Integer uid = getUidFromSession(session);
        address.setUid(uid);
        String username = session.getAttribute("username").toString();
        addressService.addnew(address,username);
        return new ResponseResult<>(SUCCESS);
    }

    @GetMapping
    public ResponseResult<List<Address>> getByUid(HttpSession session){
        Integer uid =  getUidFromSession(session);
        List<Address> list =  addressService.getByUid(uid);
        ResponseResult<List<Address>> rr = new ResponseResult<>();
        rr.setData(list);
        rr.setState(SUCCESS);
        return  rr;
    }


}
