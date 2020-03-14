package com.cyzy.dao;

import java.util.List;

import com.cyzy.bean.OrderTime;

public interface OrderTimeDao {
   //根据咨询师ID和日期，查出可预约时间
   public List<OrderTime> queryOrderTime(int userId,String orderDate);
   //根据咨询师ID
   public List<OrderTime> queryOrderTime(int userId);
   
   
   
   public List<OrderTime> queryOrderTime(OrderTime orderTime);
   
   //先删除掉当前日期的时间
   public int deleteTodayOrderTime(String orderDate);
   
   //保存预约时间，咨询师ID，日期，选中的时间点数组
   public int saveOrderTime(int userId,String orderDate,String[] orderHour) throws Exception;
   
}
