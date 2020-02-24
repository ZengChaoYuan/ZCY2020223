package com.cyzy.service;

import java.util.List;

import com.cyzy.bean.User;
import com.cyzy.util.Page;

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
	
	//返回page对象:里面包含页面展示的 1.数据,2.和分页相关的数据
	//参数:包含2个,1是查询条件,2是查询页码
	public Page<User> queryUsers(User user,int currentPageNum);
		
}
