package cn.tedu.store.service;

import cn.tedu.store.entity.Address;
import cn.tedu.store.service.ex.InsertException;

public interface IAddressService {

    void addnew(Address address,String username) throws InsertException;


}
