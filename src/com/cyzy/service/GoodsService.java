package com.cyzy.service;

import java.util.List;

import com.cyzy.bean.Goods;

public interface GoodsService {//��Ʒ�����ɾ�Ĳ�
	
	//ɾ��
	public int deleteGoods(int goodsId);
	
	//����ID����ȡһ��role����
	public Goods getGoodsById(int goodsId);
	//��ѯ
	public List<Goods> queryGoods(Goods Goods);
	//�޸�
	public int updateGoods(Goods goods);
	//����
	public int addGoods(Goods goods);
	
}
