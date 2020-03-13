package com.cyzy.service;

import java.util.List;

import com.cyzy.bean.OrderTime;

public interface OrderTimeService {
	   //根据咨询师ID和日期，查出可预约时间
	   public List<OrderTime> queryOrderTime(int userId,String orderDate);
	   
	   public List<OrderTime> queryOrderTime(int userId);
}
