package cn.tedu.store.service.impl;


import cn.tedu.store.entity.Address;
import cn.tedu.store.mapper.AddressMapper;
import cn.tedu.store.service.IAddressService;
import cn.tedu.store.service.ex.InsertException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class AddressServiceImpl implements IAddressService {

    @Autowired
    AddressMapper mapper;

    @Override
    public void addnew(Address address, String username) throws InsertException {
        Integer uid = address.getUid();
        Integer count = countByUid(uid);
        Integer isDefault = count == 0 ? 1 : 0 ;
        address.setIsDefault(isDefault);
        Date now = new Date();
        address.setCreatedUser(username);
        address.setCreatedTime(now);
        address.setModifiedUser(username);
        address.setModifiedTime(now);
        insert(address);

    }

    @Override
    public List<Address> getByUid(Integer uid) {
        return  findByUid(uid);
    }

    private List<Address> findByUid(Integer uid){
        return mapper.findByUid(uid);
    }


    /**
     * 增加收货地址
     * @param address 收货地址数据
     */
    private void  insert(Address address){
        Integer rows = mapper.insert(address);
        if(rows!=1){
            throw new InsertException("增加收货地址失败，插入数据是出现未知错误");
        }
    }

    /**
     * 统计某用户的收货地址数量
     * @param uid 用户的id
     * @return 收获地址的数量
     */
    private Integer countByUid(Integer uid){
        return mapper.countByUid(uid);
    }

}
