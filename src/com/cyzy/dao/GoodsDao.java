package com.cyzy.dao;

import java.util.List;

import com.cyzy.bean.Goods;

public interface GoodsDao {//商品表的增删改查
	//增加
	public int addGoods(Goods goods);
	//删除
	public int deleteGoods(int goodsId);
	//修改
	public int updateGoods(Goods goods);
	//查询
	public List<Goods> queryGoods(Goods goods);
  
}
