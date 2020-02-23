package com.cyzy.service;

import java.util.List;

import com.cyzy.bean.User;

public interface UserService {
	
  
	//根据ID来获取一个user对象
	public User getUserById(int userId);
	//查询
	public List<User> queryUsers(User user);
	//增加
	public int addUser(User user);
	//删除
	public int deleteUser(int userId);
	//修改
	public int updateUser(User user);
	
	//登录
	public User login(String userName,String password);
		
}
