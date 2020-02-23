package com.cyzy.dao;

import java.util.List;

import com.cyzy.bean.User;

public interface UserDao {

	// 插入用户, 注册功能
	public int addUser(User user);

	// 查询, 查询有多少用户已经注册
	public List<User> queryUsers(User user);

	// 删除,删除已经注册的用户 
	public int deleteUser(int userId);

	// 更新表中的数据
	public int updateUser(User user);
	//根据用户Id来修改用户
	public User updateUser(int userId);
    
	public User loginUser(User user);
	
	//根据用户Id来修改用户,查看用户详情
	public User getUserById(int userId);
	
	//登录
	public User login(String userName,String password);
	
	//没用上
	public int createUserId();
	
}
