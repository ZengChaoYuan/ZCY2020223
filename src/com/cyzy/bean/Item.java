package com.cyzy.bean;

public class Item {
  private int itemId;
  private int titleId;
  private String name;
  private int scope;
  
  
  
public Item(int itemId, int titleId, String name, int scope) {
	super();
	this.itemId = itemId;
	this.titleId = titleId;
	this.name = name;
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
public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}
public int getScope() {
	return scope;
}
public void setScope(int scope) {
	this.scope = scope;
}
  
  
}
