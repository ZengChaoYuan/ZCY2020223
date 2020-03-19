package com.cyzy.service;

import java.util.List;
import java.util.Map;

import com.cyzy.bean.MyAccount;
import com.cyzy.dao.MyAccountDao;
import com.cyzy.util.DaoFactory;
import com.cyzy.util.Page;



public class MyAccountServiceImpl implements MyAccountService {

	@Override
	public List<MyAccount> queryMyAccount(int customerId) {//用户查看自己的账户
		MyAccountDao myAccountDao=(MyAccountDao)DaoFactory.getDaoImpl(MyAccountDao.class.getName());
		return myAccountDao.queryMyAccount(customerId);
	}

	@Override
	public List<MyAccount> queryfundAccount(int userId) {//咨询师查看自己的账户
		MyAccountDao myAccountDao=(MyAccountDao)DaoFactory.getDaoImpl(MyAccountDao.class.getName());
		return myAccountDao.queryfundAccount(userId);
	}
	
	@Override
	public int addCustomerAccount(MyAccount myAccount) throws Exception {
		MyAccountDao myAccountDao=(MyAccountDao)DaoFactory.getDaoImpl(MyAccountDao.class.getName());
		return myAccountDao.addCustomerAccount(myAccount);
	}

	@Override
	public List<Map<String, Object>> queryCustomerAccount(int customerId) {
		MyAccountDao myAccountDao=(MyAccountDao)DaoFactory.getDaoImpl(MyAccountDao.class.getName());
		return myAccountDao.queryCustomerAccount(customerId);
	}

	//客户
	@Override
	public Page<Map<String, Object>> queryCustomerAccounts(MyAccount myAccount, int currentPageNum) {
		MyAccountDao myAccountDao=(MyAccountDao)DaoFactory.getDaoImpl(MyAccountDao.class.getName());
		int totalRecordsNum=myAccountDao.queryCustomerCount(myAccount);
		Page<Map<String,Object>> page=new Page<Map<String,Object>>(currentPageNum,totalRecordsNum,5);
		List<Map<String,Object>> customerCounts=myAccountDao.queryCustomerAccounts(myAccount, page.getStartIndex(),page.getEndIndex());
		page.setRecords(customerCounts);
		return page;
	}
	//咨询师
	@Override
	public Page<Map<String, Object>> queryUserFunds(MyAccount myAccount, int currentPageNum) {
		MyAccountDao myAccountDao=(MyAccountDao)DaoFactory.getDaoImpl(MyAccountDao.class.getName());
		int totalRecordsNum=myAccountDao.queryUserCount(myAccount);
		Page<Map<String,Object>> page=new Page<Map<String,Object>>(currentPageNum,totalRecordsNum,5);
		List<Map<String,Object>> userFunds=myAccountDao.queryUserFunds(myAccount, page.getStartIndex(),page.getEndIndex());
		page.setRecords(userFunds);
		return page;
	}

	@Override
	public List<Map<String, Object>> queryUserFund(int userId) {
		MyAccountDao myAccountDao=(MyAccountDao)DaoFactory.getDaoImpl(MyAccountDao.class.getName());
		return myAccountDao.queryUserFund(userId);
	}

	

	

}
