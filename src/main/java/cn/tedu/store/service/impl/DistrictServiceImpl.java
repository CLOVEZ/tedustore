package cn.tedu.store.service.impl;

import cn.tedu.store.entity.District;
import cn.tedu.store.mapper.DistrictMapper;
import cn.tedu.store.service.IDistrictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 处理省\市\区数据的业务层实现类
 */
@Service
public class DistrictServiceImpl implements IDistrictService {

    @Autowired
    private DistrictMapper districtMapper;

    @Override
    public List<District> getByParent(String parent) {
        return null;
    }

    /**
     * 根据父级代号获取全国所有省、某省的所有市、某市的所有区
     * @param parent 父级代号
     * @return 全国所有省、某省的所有市、某市的所有区的列表
     */
    private List<District> findByParent(String parent){
        return districtMapper.findByParent(parent);
    }


}
