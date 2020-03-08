package com.cyzy.bean;

public class Goods {

	private int goodsId;
	private String goodsName;
	private String goodsLClass;
	private String goodsSClass;
	private int status;

	public Goods(int goodsId, String goodsName, String goodsLClass, String goodsSClass, int status) {
		super();
		this.goodsId = goodsId;
		this.goodsName = goodsName;
		this.goodsLClass = goodsLClass;
		this.goodsSClass = goodsSClass;
		this.status = status;
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



	public String getGoodsLClass() {
		return goodsLClass;
	}



	public void setGoodsLClass(String goodsLClass) {
		this.goodsLClass = goodsLClass;
	}



	public String getGoodsSClass() {
		return goodsSClass;
	}



	public void setGoodsSClass(String goodsSClass) {
		this.goodsSClass = goodsSClass;
	}



	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

}
