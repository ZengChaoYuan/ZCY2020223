package com.cyzy.bean;

public class Log {//��ѯʱ��,���ܺͽ�����
	private String title;
	private int count;

	public Log(String title, int count) {
		super();
		this.title = title;
		this.count = count;
	}
	
	public Log() {
		super();
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}



	public int getCount() {
		return count;
	}



	public void setCount(int count) {
		this.count = count;
	}

}
