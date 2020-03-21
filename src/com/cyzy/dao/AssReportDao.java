package com.cyzy.dao;

import java.util.List;
import java.util.Map;
import com.cyzy.bean.AssReport;
public interface AssReportDao {
  //答题后自动生成的评估报告
  public int addAssReport(AssReport assReport) throws Exception;
  //客户（我的报告列表）
  public List<AssReport> querymyReport(int customerId);
  //客户查看自己的报告列表,分页
  public int MyReportCounts(AssReport assReport);  
  public List<Map<String,Object>> queryMyReports(AssReport assReport,int startIndex,int endIndex);
  
  //管理员查看用户评估列表
  public List<Map<String,Object>> queryCustomerReport(AssReport assReport);
  //管理员查看用户评估列表,分页
  public int AllReportCounts();  
  public List<Map<String,Object>> queryAllReports(int startIndex,int endIndex);
  
  //管理员查看用户的评测报告
  public Map<String,Object> queryCustomerAss(int assReportId);
  
}
