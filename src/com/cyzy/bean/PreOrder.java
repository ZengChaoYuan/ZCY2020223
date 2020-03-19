package com.cyzy.bean;

public class PreOrder {// 预约表
	private int preorderId;// 预约ID
	private int userId;// 咨询师ID
	private String problemDesc;// 问题描述
	private String diagnoseReply;// 诊断答复
	private String evaluateContent;// 评价内容
	private int preorderPrice;// 预约价格
	private int areaId;// 领域ID
	private int customerId;// 客户ID
	private int orderStatus;// 预约状态
	private String preorderTime;// 预约时间
    private String replyTime;//答复时间
    
	

	public PreOrder(int preorderId, int userId, String problemDesc, String diagnoseReply, String evaluateContent,
			int preorderPrice, int areaId, int customerId, int orderStatus, String preorderTime, String replyTime) {
		super();
		this.preorderId = preorderId;
		this.userId = userId;
		this.problemDesc = problemDesc;
		this.diagnoseReply = diagnoseReply;
		this.evaluateContent = evaluateContent;
		this.preorderPrice = preorderPrice;
		this.areaId = areaId;
		this.customerId = customerId;
		this.orderStatus = orderStatus;
		this.preorderTime = preorderTime;
		this.replyTime = replyTime;
	}

	public PreOrder() {
		super();
	}

	public int getPreorderId() {
		return preorderId;
	}

	public void setPreorderId(int preorderId) {
		this.preorderId = preorderId;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getProblemDesc() {
		return problemDesc;
	}

	public void setProblemDesc(String problemDesc) {
		this.problemDesc = problemDesc;
	}

	public String getDiagnoseReply() {
		return diagnoseReply;
	}

	public void setDiagnoseReply(String diagnoseReply) {
		this.diagnoseReply = diagnoseReply;
	}

	public String getEvaluateContent() {
		return evaluateContent;
	}

	public void setEvaluateContent(String evaluateContent) {
		this.evaluateContent = evaluateContent;
	}

	public int getPreorderPrice() {
		return preorderPrice;
	}

	public void setPreorderPrice(int preorderPrice) {
		this.preorderPrice = preorderPrice;
	}

	public int getAreaId() {
		return areaId;
	}

	public void setAreaId(int areaId) {
		this.areaId = areaId;
	}

	public int getCustomerId() {
		return customerId;
	}

	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}

	public int getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(int orderStatus) {
		this.orderStatus = orderStatus;
	}

	public String getPreorderTime() {
		return preorderTime;
	}

	public void setPreorderTime(String preorderTime) {
		this.preorderTime = preorderTime;
	}

	public String getReplyTime() {
		return replyTime;
	}

	public void setReplyTime(String replyTime) {
		this.replyTime = replyTime;
	}
		
}
