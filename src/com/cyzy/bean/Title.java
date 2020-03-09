package com.cyzy.bean;

import java.util.ArrayList;
import java.util.List;

public class Title {
	private int titleId;
	private int areaId;
	private String name;
	private List<Item> items=new ArrayList<Item>();

	public Title(int titleId, int areaId, String name) {
		super();
		this.titleId = titleId;
		this.areaId = areaId;
		this.name = name;
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Item> getItems() {
		return items;
	}

	public void setItems(List<Item> items) {
		this.items = items;
	}
	
}
