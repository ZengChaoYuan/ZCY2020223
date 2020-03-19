package com.cyzy.dao;

import java.util.List;
import java.util.Map;

import com.cyzy.bean.MyAccount;

public interface MyAccountDao {
  //�ͻ��鿴�Լ����˻�
  public List<MyAccount> queryMyAccount(int customerId);
  //�ͻ��鿴�Լ����˻�
  public List<Map<String,Object>> queryCustomerAccount(int customerId);
  //�ͻ��鿴�Լ����˻�,��ҳ
  public int queryCustomerCount(MyAccount myAccount);
  public List<Map<String,Object>> queryCustomerAccounts(MyAccount myAccount,int startIndex,int endIndex);
  
  //�ͻ���ֵ�����Ӳ���,����ѯʦȷ��ԤԼ
  public int addCustomerAccount(MyAccount myAccount) throws Exception;
  
  //��ѯʦ�鿴�Լ����˻�
  public List<MyAccount> queryfundAccount(int userId);
  //��ѯʦ�鿴�Լ����˻�,Map
  public List<Map<String,Object>> queryUserFund(int userId);
  //��ѯʦ�鿴�Լ����˻�����ҳ
  public int queryUserCount(MyAccount myAccount);
  public List<Map<String,Object>> queryUserFunds(MyAccount myAccount,int startIndex,int endIndex);
  
}
