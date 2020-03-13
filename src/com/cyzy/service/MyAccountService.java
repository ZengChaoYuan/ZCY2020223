package com.cyzy.service;

import java.util.List;

import com.cyzy.bean.MyAccount;

public interface MyAccountService {
	  //客户查看自己的账户
	  public List<MyAccount> queryMyAccount(int customerId);
	  
	  //客户充值，增加操作
	  public int addCustomerAccount(MyAccount myAccount) throws Exception;
	  
	  //咨询师查看自己的账户
	  public List<MyAccount> queryfundAccount(int userId);
}
