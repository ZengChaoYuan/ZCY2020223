package com.cyzy.util;

import java.util.List;

public class Page<T> {
	private int currentPageNum;// 当前页
	private int totalPageNum;// 总页数
	private int totalRecordsNum;// 总记录数
	private int pageSize;// 每页记录数
	private List<T> records;// 符合条件的数据记录
	// T,泛型,不知道是用户还是角色

	// 用于数据库查询用
	private int startIndex;// 查询开始的索引值
	private int endIndex;// 查询结束的索引值

	// 用于显示页面页码
	private int prevPageNum;// 上一页页码
	private int nextPageNum;// 下一页页码

	public Page() {
		super();
	}

	public Page(int currentPageNum, int totalRecordsNum, int pageSize) {
		super();
		this.currentPageNum = currentPageNum;
		this.totalRecordsNum = totalRecordsNum;
		this.pageSize = pageSize;

		// 计算总页数
		totalPageNum = (totalRecordsNum % pageSize == 0 ? totalRecordsNum / pageSize
				: (totalRecordsNum / pageSize) + 1) ;
		// 上一页
		prevPageNum = currentPageNum - 1 > 0 ? currentPageNum - 1 : 1;
		//下一页
		nextPageNum = currentPageNum + 1 > totalPageNum ? currentPageNum : currentPageNum + 1;
		
		// 数据开始的索引值,第二页，每页5条,得到6,10
		startIndex = (currentPageNum - 1) * pageSize + 1;
		endIndex = currentPageNum * pageSize;
	}
	

	public int getCurrentPageNum() {
		return currentPageNum;
	}

	public void setCurrentPageNum(int currentPageNum) {
		this.currentPageNum = currentPageNum;
	}

	public int getTotalPageNum() {
		return totalPageNum;
	}

	public void setTotalPageNum(int totalPageNum) {
		this.totalPageNum = totalPageNum;
	}

	public int getTotalRecordsNum() {
		return totalRecordsNum;
	}

	public void setTotalRecordsNum(int totalRecordsNum) {
		this.totalRecordsNum = totalRecordsNum;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public List<T> getRecords() {
		return records;
	}

	public void setRecords(List<T> records) {
		this.records = records;
	}

	public int getStartIndex() {
		return startIndex;
	}

	public void setStartIndex(int startIndex) {
		this.startIndex = startIndex;
	}

	public int getEndIndex() {
		return endIndex;
	}

	public void setEndIndex(int endIndex) {
		this.endIndex = endIndex;
	}

	public int getPrevPageNum() {
		return prevPageNum;
	}

	public void setPrevPageNum(int prevPageNum) {
		this.prevPageNum = prevPageNum;
	}

	public int getNextPageNum() {
		return nextPageNum;
	}

	public void setNextPageNum(int nextPageNum) {
		this.nextPageNum = nextPageNum;
	}

}
