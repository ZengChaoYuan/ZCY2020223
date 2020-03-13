package com.cyzy.bean;

public class Stand {
	private int standId;
	private int areaId;
	private int maxScore;
	private int minScore;
	private String assResult;
	private String reportContent;

	public Stand(int standId, int areaId, int maxScore, int minScore, String assResult, String reportContent) {
		super();
		this.standId = standId;
		this.areaId = areaId;
		this.maxScore = maxScore;
		this.minScore = minScore;
		this.assResult = assResult;
		this.reportContent = reportContent;
	}

	public Stand() {
		super();
	}

	public int getStandId() {
		return standId;
	}

	public void setStandId(int standId) {
		this.standId = standId;
	}

	public int getAreaId() {
		return areaId;
	}

	public void setAreaId(int areaId) {
		this.areaId = areaId;
	}

	public int getMaxScore() {
		return maxScore;
	}

	public void setMaxScore(int maxScore) {
		this.maxScore = maxScore;
	}

	public int getMinScore() {
		return minScore;
	}

	public void setMinScore(int minScore) {
		this.minScore = minScore;
	}

	public String getAssResult() {
		return assResult;
	}

	public void setAssResult(String assResult) {
		this.assResult = assResult;
	}

	public String getReportContent() {
		return reportContent;
	}

	public void setReportContent(String reportContent) {
		this.reportContent = reportContent;
	}

}
