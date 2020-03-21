package com.cyzy.dao;

import java.util.List;
import java.util.Map;
import com.cyzy.bean.AssReport;
public interface AssReportDao {
  //������Զ����ɵ���������
  public int addAssReport(AssReport assReport) throws Exception;
  //�ͻ����ҵı����б�
  public List<AssReport> querymyReport(int customerId);
  //�ͻ��鿴�Լ��ı����б�,��ҳ
  public int MyReportCounts(AssReport assReport);  
  public List<Map<String,Object>> queryMyReports(AssReport assReport,int startIndex,int endIndex);
  
  //����Ա�鿴�û������б�
  public List<Map<String,Object>> queryCustomerReport(AssReport assReport);
  //����Ա�鿴�û������б�,��ҳ
  public int AllReportCounts();  
  public List<Map<String,Object>> queryAllReports(int startIndex,int endIndex);
  
  //����Ա�鿴�û������ⱨ��
  public Map<String,Object> queryCustomerAss(int assReportId);
  
}
