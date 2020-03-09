package com.cyzy.bean;

import java.util.ArrayList;
import java.util.List;
//准备Ajax扔到前端的数据
public class JsonMessage {
  private int id;//编号
  private String msg;//消息
  private String location;//跳转路径
  private List<Object> dataList=new ArrayList<Object>();//业务数据
  //private Page page;
  
public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
}
public String getMsg() {
	return msg;
}
public void setMsg(String msg) {
	this.msg = msg;
}
public String getLocation() {
	return location;
}
public void setLocation(String location) {
	this.location = location;
}
public List<Object> getDataList() {
	return dataList;
}
public void setDataList(List<Object> dataList) {
	this.dataList = dataList;
}
  
}
