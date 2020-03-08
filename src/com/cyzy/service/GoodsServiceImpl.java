package com.cyzy.service;

import java.util.List;

import com.cyzy.bean.Goods;
import com.cyzy.dao.GoodsDao;
import com.cyzy.util.DaoFactory;

public class GoodsServiceImpl implements GoodsService {

	@Override
	public int addGoods(Goods goods) {
		GoodsDao goodsDao=(GoodsDao)DaoFactory.getDaoImpl(GoodsDao.class.getName());
		return goodsDao.addGoods(goods);
	}

	@Override
	public int deleteGoods(int goodsId) {
		GoodsDao goodsDao=(GoodsDao)DaoFactory.getDaoImpl(GoodsDao.class.getName());
		return goodsDao.deleteGoods(goodsId);
	}

	@Override
	public int updateGoods(Goods goods) {
		GoodsDao goodsDao=(GoodsDao)DaoFactory.getDaoImpl(GoodsDao.class.getName());
		return goodsDao.updateGoods(goods);
	}
    //²é¿´ÏêÇé
	@Override
	public Goods getGoodsById(int goodsId) {
		GoodsDao goodsDao=(GoodsDao)DaoFactory.getDaoImpl(GoodsDao.class.getName());
		return goodsDao.getGoodsById(goodsId);
	}

	@Override
	public List<Goods> queryGoods(Goods Goods) {
		GoodsDao goodsDao=(GoodsDao)DaoFactory.getDaoImpl(GoodsDao.class.getName());
		return goodsDao.queryGoods(Goods);
	}

}
