package com.cyzy.service;

import java.util.List;

import com.cyzy.bean.OrderTime;

public interface OrderTimeService {
	   //������ѯʦID�����ڣ������ԤԼʱ��
	   public List<OrderTime> queryOrderTime(int userId,String orderDate);
	   
	   public List<OrderTime> queryOrderTime(int userId);
}
