package com.cyzy.service;

import java.util.List;
import java.util.Map;

import com.cyzy.bean.AssReport;
import com.cyzy.util.Page;

public interface AssReportService {
	  //答题后自动生成的评估报告
	  public int addAssReport(AssReport assReport) throws Exception;
	  //客户（我的报告列表）
	  public List<AssReport> querymyReport(int customerId);
	  //客户查看自己的报告列表,分页
	  public Page<Map<String,Object>> querymyReports(AssReport assReport, int currentPageNum);
	  //管理员查看用户评估列表
	  public List<Map<String,Object>> queryCustomerReport(AssReport assReport);
	  //分页
	  public Page<Map<String,Object>> queryAllReports(int currentPageNum);
	  
	  //管理员查看用户的评测报告
	  public Map<String,Object> queryCustomerAss(int assReportId);
}
