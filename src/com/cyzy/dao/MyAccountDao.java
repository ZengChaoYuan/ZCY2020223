package com.cyzy.dao;

import java.util.List;

import com.cyzy.bean.MyAccount;

public interface MyAccountDao {
  //�ͻ��鿴�Լ����˻�
  public List<MyAccount> queryMyAccount(int customerId);
  
  //�ͻ���ֵ�����Ӳ���
  public int addCustomerAccount(MyAccount myAccount) throws Exception;
  
  //��ѯʦ�鿴�Լ����˻�
  public List<MyAccount> queryfundAccount(int userId);
  
  
}
