package com.cyzy.dao;

import java.util.List;

import com.cyzy.bean.Goods;

public interface GoodsDao {//��Ʒ�����ɾ�Ĳ�
	//����
	public int addGoods(Goods goods);
	//ɾ��
	public int deleteGoods(int goodsId);
	//�޸�
	public int updateGoods(Goods goods);
	//��ѯ
	public List<Goods> queryGoods(Goods goods);
  
}
