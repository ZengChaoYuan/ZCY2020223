package com.cyzy.bean;

import java.util.ArrayList;
import java.util.List;
//׼��Ajax�ӵ�ǰ�˵�����
public class JsonMessage {
  private int id;//���
  private String msg;//��Ϣ
  private String location;//��ת·��
  private List<Object> dataList=new ArrayList<Object>();//ҵ������
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
