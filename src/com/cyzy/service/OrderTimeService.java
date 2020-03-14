package com.cyzy.service;

import java.util.List;

import com.cyzy.bean.OrderTime;

public interface OrderTimeService {
	   //根据咨询师ID和日期，查出可预约时间
	   public List<OrderTime> queryOrderTime(int userId,String orderDate);
	   
	   public List<OrderTime> queryOrderTime(int userId);
	   
	   //先删除掉当前日期的时间
	   public int deleteTodayOrderTime(String orderDate);
	   
	   //保存预约时间，咨询师ID，日期，选中的时间点数组
	   public int saveOrderTime(int userId,String orderDate,String[] orderHour) throws Exception;
}
