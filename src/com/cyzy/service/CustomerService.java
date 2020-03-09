package com.cyzy.service;

import java.util.List;

import com.cyzy.bean.Customer;
import com.cyzy.bean.CustomerStatis;
import com.cyzy.util.Page;

public interface CustomerService {
	  //登录
	  public Customer login(String customerName,String password);
	  
	  //注册
	  public int addCustomer(Customer customer) throws Exception;
	  
	  //分页带条件
	  public Page<Customer> queryCustomerLike(Customer customer,int currentPageNum);
	  //分页不带条件
	  public Page<Customer> queryCustomerList(Customer customer,int currentPageNum);
	  
	  //根据客户ID来拿到客户的完整信息
	  public Customer getCustomerById(int customerId);
	  
	  //修改用户的状态:启用/禁用
	  public int updateUseStatus(Customer customer);
	  //修改用户的状态:逻辑删除,只改变状态
	  public int updateDeleteStatus(Customer customer);
	  //初始化客户密码
	  public int resetPassword(Customer customer);
	  //用戶统计
	  //按时间查询注册用户:本周
	  public List<CustomerStatis> queryAllCustomerByWeek(CustomerStatis customerStatis);
	  //按时间查询注册用户:本月
	  public List<CustomerStatis> queryAllCustomerByMonths(CustomerStatis customerStatis);
	  //按时间查询注册用户:近半年
	  public List<CustomerStatis> queryAllCustomerByYear(CustomerStatis customerStatis);
}
