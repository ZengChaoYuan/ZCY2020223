package com.cyzy.service;


import java.util.List;
import java.util.Map;

import com.cyzy.bean.LogInf;
import com.cyzy.bean.OrderCount;
import com.cyzy.bean.PreOrderCount;
import com.cyzy.bean.User;
import com.cyzy.dao.UserDao;

import com.cyzy.util.DaoFactory;
import com.cyzy.util.Page;


public class UserServiceImpl implements UserService {

	@Override
	public User checkUserName(String userName) {
		UserDao userDao=(UserDao)DaoFactory.getDaoImpl(UserDao.class.getName());
		return userDao.checkUserName(userName);
	}
	
	@Override
	public Page<User> queryUserList(User user, int currentPageNum) {
		UserDao userDao=(UserDao)DaoFactory.getDaoImpl(UserDao.class.getName());
		int totalRecordsNum=userDao.queryCount(user);
		//获得分页对象,还差一个数据记录(业务数据)
		Page<User> page=new Page<User>(currentPageNum,totalRecordsNum,5);
		//查询符合条件指定数据
		List<User> userList=userDao.queryUserList(user,page.getStartIndex(),page.getEndIndex());
		page.setRecords(userList);
		return page;
	}
	
	//登录
	@Override
	public User login(String userName, String password) {
		UserDao userDao=(UserDao)DaoFactory.getDaoImpl(UserDao.class.getName());
		return userDao.login(userName, password);
	}

	@Override
	public User getUserById(int userId) {
		UserDao userDao=(UserDao)DaoFactory.getDaoImpl(UserDao.class.getName());
		return userDao.getUserById(userId);
	}
	
	@Override
	public int updateUseStatus(User user) {
		UserDao userDao=(UserDao)DaoFactory.getDaoImpl(UserDao.class.getName());
		return userDao.updateUseStatus(user);
	}

	@Override
	public int updateDeleteStatus(User user) {
		UserDao userDao=(UserDao)DaoFactory.getDaoImpl(UserDao.class.getName());
		return userDao.updateDeleteStatus(user);
	}

	@Override
	public int resetPassword(User user) {
		UserDao userDao=(UserDao)DaoFactory.getDaoImpl(UserDao.class.getName());
		return userDao.resetPassword(user);
	}
	
	
	
	@Override
	public List<User> queryUsers(User user) {
		UserDao userDao=(UserDao)DaoFactory.getDaoImpl(UserDao.class.getName());
		return userDao.queryUsers(user);
	}

	@Override
	public int addUser(User user) throws Exception {
		UserDao userDao=(UserDao)DaoFactory.getDaoImpl(UserDao.class.getName());
		return userDao.addUser(user);
	}

	@Override
	public int deleteUser(int userId) {
		UserDao userDao=(UserDao)DaoFactory.getDaoImpl(UserDao.class.getName());
		return userDao.deleteUser(userId);
	}

	

	@Override
	public int updateUser(User user) {
		UserDao userDao=(UserDao)DaoFactory.getDaoImpl(UserDao.class.getName());
		return userDao.updateUser(user);
	}

	@Override
	public Map<String, Object> querySingUserByUserId(int userId) {
		UserDao userDao=(UserDao)DaoFactory.getDaoImpl(UserDao.class.getName());
		return userDao.querySingUserByUserId(userId);
	}

	@Override
	public int updateBalance(User user) {
		UserDao userDao=(UserDao)DaoFactory.getDaoImpl(UserDao.class.getName());
		return userDao.updateBalance(user);
	}

	@Override
	public List<OrderCount> queryAllOrderCountByUser(String startTime, String endTime) {
		UserDao userDao=(UserDao)DaoFactory.getDaoImpl(UserDao.class.getName());
		return userDao.queryAllOrderCountByUser(startTime, endTime);
	}

	@Override
	public int addOrderCount(PreOrderCount preOrderCount) throws Exception {
		UserDao userDao=(UserDao)DaoFactory.getDaoImpl(UserDao.class.getName());
		return userDao.addOrderCount(preOrderCount);
	}


}
