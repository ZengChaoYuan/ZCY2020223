package com.cyzy.util;

import java.util.List;

public class Page<T> {
	private int currentPageNum;// ��ǰҳ
	private int totalPageNum;// ��ҳ��
	private int totalRecordsNum;// �ܼ�¼��
	private int pageSize;// ÿҳ��¼��
	private List<T> records;// �������������ݼ�¼
	// T,����,��֪�����û����ǽ�ɫ

	// �������ݿ��ѯ��
	private int startIndex;// ��ѯ��ʼ������ֵ
	private int endIndex;// ��ѯ����������ֵ

	// ������ʾҳ��ҳ��
	private int prevPageNum;// ��һҳҳ��
	private int nextPageNum;// ��һҳҳ��

	public Page() {
		super();
	}

	public Page(int currentPageNum, int totalRecordsNum, int pageSize) {
		super();
		this.currentPageNum = currentPageNum;
		this.totalRecordsNum = totalRecordsNum;
		this.pageSize = pageSize;

		// ������ҳ��
		totalPageNum = (totalRecordsNum % pageSize == 0 ? totalRecordsNum / pageSize
				: (totalRecordsNum / pageSize) + 1) ;
		// ��һҳ
		prevPageNum = currentPageNum - 1 > 0 ? currentPageNum - 1 : 1;
		//��һҳ
		nextPageNum = currentPageNum + 1 > totalPageNum ? currentPageNum : currentPageNum + 1;
		
		// ���ݿ�ʼ������ֵ,�ڶ�ҳ��ÿҳ5��,�õ�6,10
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
