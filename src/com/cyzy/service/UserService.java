package com.cyzy.service;

import java.util.List;
import java.util.Map;

import com.cyzy.bean.User;
import com.cyzy.util.Page;

public interface UserService {
	//修改咨询师余额
	public int updateBalance(User user);
	
	//单个用户
	public Map<String,Object> querySingUserByUserId(int userId);
	
	// 检测用户名是否重复
	public User checkUserName(String userName);

	// 登录
	public User login(String userName, String password);

	// 分页查询不带条件
	public Page<User> queryUserList(User user, int currentPageNum);

	// 通过用户ID来获得后台用户的完整信息
	public User getUserById(int userId);

	// 修改用户状态:启用/禁用
	public int updateUseStatus(User user);
	// 修改用户的状态:逻辑删除,只改变状态
	public int updateDeleteStatus(User user);
	// 初始化客户密码
	public int resetPassword(User user);

	// 查询
	public List<User> queryUsers(User user);

	// 增加
	public int addUser(User user) throws Exception;

	// 删除
	public int deleteUser(int userId);

	// 修改
	public int updateUser(User user);

}
