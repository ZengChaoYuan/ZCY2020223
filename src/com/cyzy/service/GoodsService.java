package com.cyzy.service;

import java.util.List;

import com.cyzy.bean.Goods;

public interface GoodsService {//��Ʒ�����ɾ�Ĳ�
	//����
	public int addGoods(Goods goods);
	//ɾ��
	public int deleteGoods(int goodsId);
	//�޸�
	public int updateGoods(Goods goods);
	//����ID����ȡһ��role����
	public Goods getGoodsById(int goodsId);
	//��ѯ
	public List<Goods> queryGoods(Goods Goods);
  
}
