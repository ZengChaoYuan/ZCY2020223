package com.cyzy.bean;

public class Item {//选项表
  private int itemId;//选项ID
  private int titleId;//题目ID
  private String itemName;//选项名称
  private int scope;
    
public Item(int itemId, int titleId, String itemName, int scope) {
	super();
	this.itemId = itemId;
	this.titleId = titleId;
	this.itemName = itemName;
	this.scope = scope;
}

public Item() {
	super();
}

public int getItemId() {
	return itemId;
}

public void setItemId(int itemId) {
	this.itemId = itemId;
}

public int getTitleId() {
	return titleId;
}

public void setTitleId(int titleId) {
	this.titleId = titleId;
}

public String getItemName() {
	return itemName;
}

public void setItemName(String itemName) {
	this.itemName = itemName;
}

public int getScope() {
	return scope;
}

public void setScope(int scope) {
	this.scope = scope;
}

}
