package com.cyzy.service;

import java.util.List;
import java.util.Map;

import com.cyzy.bean.AssReport;

public interface AssReportService {
	  //�ͻ����ҵı����б�
	  public List<AssReport> querymyReport(int customerId);
	  
	  //����Ա�鿴�û������б�
	  public List<Map<String,Object>> queryCustomerReport(AssReport assReport);
	  
	  //����Ա�鿴�û������ⱨ��
	  public Map<String,Object> queryCustomerAss(int assReportId);
}
