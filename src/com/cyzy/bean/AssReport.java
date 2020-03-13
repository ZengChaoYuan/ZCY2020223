package com.cyzy.bean;

public class AssReport {
	private int assreportId;
	private String assTime;
	private int standId;
	private int assScore;
	private int customerId;

	public AssReport(int assreportId, String assTime, int standId, int assScore, int customerId) {
		super();
		this.assreportId = assreportId;
		this.assTime = assTime;
		this.standId = standId;
		this.assScore = assScore;
		this.customerId = customerId;
	}

	public AssReport() {
		super();
	}

	public int getAssreportId() {
		return assreportId;
	}

	public void setAssreportId(int assreportId) {
		this.assreportId = assreportId;
	}

	public String getAssTime() {
		return assTime;
	}

	public void setAssTime(String assTime) {
		this.assTime = assTime;
	}

	public int getStandId() {
		return standId;
	}

	public void setStandId(int standId) {
		this.standId = standId;
	}

	public int getAssScore() {
		return assScore;
	}

	public void setAssScore(int assScore) {
		this.assScore = assScore;
	}

	public int getCustomerId() {
		return customerId;
	}

	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}

}
