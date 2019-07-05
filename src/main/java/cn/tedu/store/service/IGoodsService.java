package cn.tedu.store.service;

import java.util.List;

import cn.tedu.store.entity.Goods;

public interface IGoodsService {

	/**
	 * 获取热销的前4项商品的数据列表
	 * @return 热销的前4项商品的数据列表
	 */
	List<Goods> getHotList();
	
	/**
	 * 根据商品id查询商品详情
	 * @param id 商品id
	 * @return 匹配的商品详情，如果没有匹配的数据，则返回null
	 */
	Goods getById(Long id);
	
}











