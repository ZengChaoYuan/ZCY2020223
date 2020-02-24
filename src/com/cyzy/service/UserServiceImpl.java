package com.cyzy.service;


import java.util.List;

import com.cyzy.bean.User;
import com.cyzy.dao.UserDao;

import com.cyzy.util.DaoFactory;
import com.cyzy.util.Page;


public class UserServiceImpl implements UserService {

	
	@Override
	public Page<User> queryUsers(User user, int currentPageNum) {
		UserDao userDao=(UserDao)DaoFactory.getDaoImpl(UserDao.class.getName());
		int totalRecordsNum=userDao.queryCount(user);
		//获得分页对象,还差一个数据记录(业务数据)
		Page<User> page=new Page<User>(currentPageNum,totalRecordsNum,5);
		//查询符合条件指定数据
		List<User> userList=userDao.queryUsers(user,page.getStartIndex(),page.getEndIndex());
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
	public List<User> queryUsers(User user) {
		UserDao userDao=(UserDao)DaoFactory.getDaoImpl(UserDao.class.getName());
		return userDao.queryUsers(user);
	}

	@Override
	public int addUser(User user) {
		UserDao userDao=(UserDao)DaoFactory.getDaoImpl(UserDao.class.getName());
		return userDao.addUser(user);
	}

	@Override
	public int deleteUser(int userId) {
		UserDao userDao=(UserDao)DaoFactory.getDaoImpl(UserDao.class.getName());
		return userDao.deleteUser(userId);
	}

	@Override
	public User getUserById(int userId) {
		UserDao userDao=(UserDao)DaoFactory.getDaoImpl(UserDao.class.getName());
		return userDao.getUserById(userId);
	}

	@Override
	public int updateUser(User user) {
		UserDao userDao=(UserDao)DaoFactory.getDaoImpl(UserDao.class.getName());
		return userDao.updateUser(user);
	}

	

	

}
