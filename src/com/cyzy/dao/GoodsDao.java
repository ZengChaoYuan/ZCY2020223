package com.cyzy.dao;

import java.util.List;

import com.cyzy.bean.Goods;

public interface GoodsDao {//��Ʒ�����ɾ�Ĳ�
	
	//ɾ��
	public int deleteGoods(int goodsId);
	
	//��ѯ
	public List<Goods> queryGoods(Goods goods);
    //�鿴����
	public Goods getGoodsById(int goodsId);
	//�޸�
	public int updateGoods(Goods goods);
	//����
	public int addGoods(Goods goods);
	
}
