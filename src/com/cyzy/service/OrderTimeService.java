package com.cyzy.service;

import java.util.List;

import com.cyzy.bean.OrderTime;

public interface OrderTimeService {
	   //������ѯʦID�����ڣ������ԤԼʱ��
	   public List<OrderTime> queryOrderTime(int userId,String orderDate);
	   
	   public List<OrderTime> queryOrderTime(int userId);
	   
	   //��ɾ������ǰ���ڵ�ʱ��
	   public int deleteTodayOrderTime(String orderDate);
	   
	   //����ԤԼʱ�䣬��ѯʦID�����ڣ�ѡ�е�ʱ�������
	   public int saveOrderTime(int userId,String orderDate,String[] orderHour) throws Exception;
}
