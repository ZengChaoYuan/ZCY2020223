package com.cyzy.bean;

public class CustomerStatis {// ��ѯ�����ͻ�:���ܡ����¡�������
	private String title;
	private int count;

	public CustomerStatis(String title, int count) {
		super();
		this.title = title;
		this.count = count;
	}

	public CustomerStatis() {
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
