package com.cyzy.dao;

import java.util.List;

import com.cyzy.bean.Customer;
import com.cyzy.bean.CustomerStatis;



public interface CustomerDao {//前台用户表
  //登录
  public Customer login(String customerName,String password);
  
  //注册
  public int addCustomer(Customer customer) throws Exception;
  
  //分页查询
  //1.查询有多少条符合条件的记录: 总记录数
  public int queryCount(Customer customer);
  //2.分页带模糊查询
  public List<Customer> queryCustomerLike(Customer customer,int startIndex,int endIndex);
  //分页不带条件
  public List<Customer> queryCustomerList(Customer customer,int startIndex,int endIndex);
  
  
  //根据客户ID来拿到客户的完整信息
  public Customer getCustomerById(int customerId);
  
  //修改用户余额
  public int updateBalance(Customer customer);
  
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
