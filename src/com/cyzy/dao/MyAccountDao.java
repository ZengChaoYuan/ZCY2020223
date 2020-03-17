package com.cyzy.dao;

import java.util.List;
import java.util.Map;

import com.cyzy.bean.MyAccount;

public interface MyAccountDao {
  //客户查看自己的账户
  public List<MyAccount> queryMyAccount(int customerId);
  //客户查看自己的账户
  public List<Map<String,Object>> queryCustomerAccount(int customerId);
  //客户查看自己的账户,分页
  public int queryCustomerCount(MyAccount myAccount);
  public List<Map<String,Object>> queryCustomerAccounts(MyAccount myAccount,int startIndex,int endIndex);
  
  //客户充值，增加操作
  public int addCustomerAccount(MyAccount myAccount) throws Exception;
  
  //咨询师查看自己的账户
  public List<MyAccount> queryfundAccount(int userId);
  
  
}
