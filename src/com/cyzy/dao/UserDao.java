package com.cyzy.dao;

import java.util.List;
import java.util.Map;
import com.cyzy.bean.OrderCount;
import com.cyzy.bean.PreOrderCount;
import com.cyzy.bean.User;

public interface UserDao {//后台用户表
	//修改咨询师余额
	public int updateBalance(User user);
	
    //单个用户
	public Map<String,Object> querySingUserByUserId(int userId);
	
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

	//管理员分配后台用户
	public int addUser(User user) throws Exception;
	
	// 查询, 查询有多少用户已经注册
	public List<User> queryUsers(User user);

	// 删除,删除已经注册的用户 
	public int deleteUser(int userId);

	// 更新表中的数据
	public int updateUser(User user);

	//用户ID
	public int createUserId();
	//新增角色为咨询师所需
	public int addUserArea(int userId,int [] areaIds) throws Exception;
	
	// 按咨询师用户查询日志:开始时间和结束时间
	public List<OrderCount> queryAllOrderCountByUser(String startTime, String endTime);
	
	//咨询师确认预约后，把数据插入到预约数量表中
	public int addOrderCount(PreOrderCount preOrderCount) throws Exception;
	
	
}
