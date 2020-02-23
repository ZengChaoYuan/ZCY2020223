package com.cyzy.dao;



import java.util.List;

import com.cyzy.bean.Goods;

public class GoodsDaoTest {
	public static void main(String[] args) {
      //增加语句
//	  GoodsDao goodsdao=new GoodsDaoImpl();
//	  Goods goods=new Goods(0,"手机");
//	  int result=goodsdao.addGoods(goods);
//	  System.out.println(result>0 ? "成功":"失败");
	  
	  //查询语句
	  GoodsDao goodsdao=new GoodsDaoImpl();
	  Goods goods=new Goods();
	  List<Goods> goodsList=goodsdao.queryGoods(goods);
	  for(int i=0;i<goodsList.size();i++) {
		  Goods g=goodsList.get(i);
		  System.out.println("商品id:"+g.getGoodsId()+", 商品名称:"+g.getGoodsName());
	  }
	  
	}
}
