package cn.tedu.store.mapper;

import cn.tedu.store.entity.District;

import java.util.List;

/**
 * 处理用户数据的持久层接口
 */
public interface DIstrictMapper {
	/**
	 * 根据父级代号获取全国所有省/某省所有市/某市所有区的列表
	 */
	List<District> findByParent(String parent);
}





