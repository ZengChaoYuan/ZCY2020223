package com.cyzy.dao;



import java.util.List;

import com.cyzy.bean.Goods;

public class GoodsDaoTest {
	public static void main(String[] args) {
      //�������
//	  GoodsDao goodsdao=new GoodsDaoImpl();
//	  Goods goods=new Goods(0,"�ֻ�");
//	  int result=goodsdao.addGoods(goods);
//	  System.out.println(result>0 ? "�ɹ�":"ʧ��");
	  
	  //��ѯ���
	  GoodsDao goodsdao=new GoodsDaoImpl();
	  Goods goods=new Goods();
	  List<Goods> goodsList=goodsdao.queryGoods(goods);
	  for(int i=0;i<goodsList.size();i++) {
		  Goods g=goodsList.get(i);
		  System.out.println("��Ʒid:"+g.getGoodsId()+", ��Ʒ����:"+g.getGoodsName());
	  }
	  
	}
}
