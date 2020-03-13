package com.cyzy.service;

import java.util.List;

import com.cyzy.bean.MyAccount;
import com.cyzy.dao.MyAccountDao;
import com.cyzy.util.DaoFactory;



public class MyAccountServiceImpl implements MyAccountService {

	@Override
	public List<MyAccount> queryMyAccount(int customerId) {//�û��鿴�Լ����˻�
		MyAccountDao myAccountDao=(MyAccountDao)DaoFactory.getDaoImpl(MyAccountDao.class.getName());
		return myAccountDao.queryMyAccount(customerId);
	}

	@Override
	public List<MyAccount> queryfundAccount(int userId) {//��ѯʦ�鿴�Լ����˻�
		MyAccountDao myAccountDao=(MyAccountDao)DaoFactory.getDaoImpl(MyAccountDao.class.getName());
		return myAccountDao.queryfundAccount(userId);
	}
	
	@Override
	public int addCustomerAccount(MyAccount myAccount) throws Exception {
		MyAccountDao myAccountDao=(MyAccountDao)DaoFactory.getDaoImpl(MyAccountDao.class.getName());
		return myAccountDao.addCustomerAccount(myAccount);
	}

	

}
