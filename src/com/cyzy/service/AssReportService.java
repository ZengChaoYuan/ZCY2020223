package com.cyzy.service;

import java.util.List;
import java.util.Map;

import com.cyzy.bean.AssReport;
import com.cyzy.util.Page;

public interface AssReportService {
	  //������Զ����ɵ���������
	  public int addAssReport(AssReport assReport) throws Exception;
	  //�ͻ����ҵı����б�
	  public List<AssReport> querymyReport(int customerId);
	  //�ͻ��鿴�Լ��ı����б�,��ҳ
	  public Page<Map<String,Object>> querymyReports(AssReport assReport, int currentPageNum);
	  //����Ա�鿴�û������б�
	  public List<Map<String,Object>> queryCustomerReport(AssReport assReport);
	  //��ҳ
	  public Page<Map<String,Object>> queryAllReports(int currentPageNum);
	  
	  //����Ա�鿴�û������ⱨ��
	  public Map<String,Object> queryCustomerAss(int assReportId);
}
