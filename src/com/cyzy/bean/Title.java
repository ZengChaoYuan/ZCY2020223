package com.cyzy.bean;

import java.util.ArrayList;
import java.util.List;

public class Title {//题目表
	private int titleId; //题目ID
	private int areaId; //评估领域ID 
	private String titleName; //题目名称
	private List<Item> items=new ArrayList<Item>();

	public Title(int titleId, int areaId, String titleName) {
		super();
		this.titleId = titleId;
		this.areaId = areaId;
		this.titleName = titleName;
	}

	public Title() {
		super();
	}

	public int getTitleId() {
		return titleId;
	}

	public void setTitleId(int titleId) {
		this.titleId = titleId;
	}

	public int getAreaId() {
		return areaId;
	}

	public void setAreaId(int areaId) {
		this.areaId = areaId;
	}

	public String getTitleName() {
		return titleName;
	}

	public void setTitleName(String titleName) {
		this.titleName = titleName;
	}

	public List<Item> getItems() {
		return items;
	}

	public void setItems(List<Item> items) {
		this.items = items;
	}
	
}
