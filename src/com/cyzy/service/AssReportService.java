package com.cyzy.service;

import java.util.List;
import java.util.Map;

import com.cyzy.bean.AssReport;

public interface AssReportService {
	  //客户（我的报告列表）
	  public List<AssReport> querymyReport(int customerId);
	  
	  //管理员查看用户评估列表
	  public List<Map<String,Object>> queryCustomerReport(AssReport assReport);
	  
	  //管理员查看用户的评测报告
	  public Map<String,Object> queryCustomerAss(int assReportId);
}
