package com.cyzy.service;

import java.util.List;

import com.cyzy.bean.Goods;

public interface GoodsService {//商品表的增删改查
	//增加
	public int addGoods(Goods goods);
	//删除
	public int deleteGoods(int goodsId);
	//修改
	public int updateGoods(Goods goods);
	//根据ID来获取一个role对象
	public Goods getGoodsById(int goodsId);
	//查询
	public List<Goods> queryGoods(Goods Goods);
  
}
