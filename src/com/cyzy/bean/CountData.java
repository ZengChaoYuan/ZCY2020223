package com.cyzy.bean;

public class CountData {
	private String title;
	private int count;

	public CountData(String title, int count) {
		super();
		this.title = title;
		this.count = count;
	}

	public CountData() {
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
