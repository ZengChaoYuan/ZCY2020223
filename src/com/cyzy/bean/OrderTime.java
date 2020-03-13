package com.cyzy.bean;

public class OrderTime {
   private int orderTimeId;//时间ID
   private int userId;//咨询师ID
   private String orderDate;//预约日期
   private int orderhour;//预约小时
   
   
public OrderTime(int orderTimeId, int userId, String orderDate, int orderhour) {
	super();
	this.orderTimeId = orderTimeId;
	this.userId = userId;
	this.orderDate = orderDate;
	this.orderhour = orderhour;
}


public OrderTime() {
	super();
}


public int getOrderTimeId() {
	return orderTimeId;
}


public void setOrderTimeId(int orderTimeId) {
	this.orderTimeId = orderTimeId;
}


public int getUserId() {
	return userId;
}


public void setUserId(int userId) {
	this.userId = userId;
}


public String getOrderDate() {
	return orderDate;
}


public void setOrderDate(String orderDate) {
	this.orderDate = orderDate;
}


public int getOrderhour() {
	return orderhour;
}


public void setOrderhour(int orderhour) {
	this.orderhour = orderhour;
}
      
}
