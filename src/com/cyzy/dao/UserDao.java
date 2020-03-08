package com.cyzy.dao;

import java.util.List;

import com.cyzy.bean.User;

public interface UserDao {//后台用户表
    
	//登录
	public User login(String userName,String password);
	
	//检测用户名是否重复
	public User checkUserName(String userName);
	
	//1.查询有多少条符合条件的记录: 总记录数
	public int queryCount(User user);
    //2.分页查询用户不带条件
	public List<User> queryUserList(User user,int startIndex,int endIndex);
	
	//通过用户Id来修改用户,查看用户详情
	public User getUserById(int userId);
	
	//修改用户状态:启用/禁用
	public int updateUseStatus(User user);	
	//修改用户的状态:逻辑删除,只改变状态
	public int updateDeleteStatus(User user);
	//初始化客户密码
	public int resetPassword(User user);

	
	
	
	public int addUser(User user) throws Exception;
	
	// 查询, 查询有多少用户已经注册
	public List<User> queryUsers(User user);

	// 删除,删除已经注册的用户 
	public int deleteUser(int userId);

	// 更新表中的数据
	public int updateUser(User user);

	//没用上
	public int createUserId();
	
}
