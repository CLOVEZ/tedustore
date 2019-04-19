package cn.tedu.store.service;

import cn.tedu.store.entity.District;

import java.util.List;

public interface IDistrictService {
    /**
     * 根据父级代号获取
     * @param parent
     * @return
     */
    List<District> getByParent(String parent);
}
