package com.cyzy.bean;

public class OrderTime {
   private int orderTimeId;//ʱ��ID
   private int userId;//��ѯʦID
   private String orderDate;//ԤԼ����
   private int orderhour;//ԤԼСʱ
   
   
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
