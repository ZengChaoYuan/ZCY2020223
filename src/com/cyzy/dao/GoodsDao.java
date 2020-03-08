package com.cyzy.dao;

import java.util.List;

import com.cyzy.bean.Goods;

public interface GoodsDao {//商品表的增删改查
	
	//删除
	public int deleteGoods(int goodsId);
	
	//查询
	public List<Goods> queryGoods(Goods goods);
    //查看详情
	public Goods getGoodsById(int goodsId);
	//修改
	public int updateGoods(Goods goods);
	//增加
	public int addGoods(Goods goods);
	
}
