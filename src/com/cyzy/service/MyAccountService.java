package com.cyzy.service;

import java.util.List;
import java.util.Map;

import com.cyzy.bean.MyAccount;
import com.cyzy.util.Page;

public interface MyAccountService {
	  //�ͻ��鿴�Լ����˻�
	  public List<MyAccount> queryMyAccount(int customerId);
	  //�ͻ��鿴�Լ����˻�
	  public List<Map<String,Object>> queryCustomerAccount(int customerId);
	  //�ͻ��鿴�Լ����˻�,��ҳ
	  public Page<Map<String,Object>> queryCustomerAccounts(MyAccount myAccount, int currentPageNum);
	  //�ͻ���ֵ�����Ӳ���
	  public int addCustomerAccount(MyAccount myAccount) throws Exception;
	  
	  //��ѯʦ�鿴�Լ����˻�
	  public List<MyAccount> queryfundAccount(int userId);
	  
	  
}
