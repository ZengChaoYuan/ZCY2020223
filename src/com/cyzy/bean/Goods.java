package com.cyzy.bean;

public class Goods {

	private int goodsId;
	private String goodsName;

	public Goods(int goodsId, String goodsName) {
		super();
		this.goodsId = goodsId;
		this.goodsName = goodsName;
	}

	public Goods() {
		super();
	}

	public int getGoodsId() {
		return goodsId;
	}

	public void setGoodsId(int goodsId) {
		this.goodsId = goodsId;
	}

	public String getGoodsName() {
		return goodsName;
	}

	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}

}
