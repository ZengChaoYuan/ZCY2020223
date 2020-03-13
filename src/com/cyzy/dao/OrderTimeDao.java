package com.cyzy.dao;

import java.util.List;

import com.cyzy.bean.OrderTime;

public interface OrderTimeDao {
   //������ѯʦID�����ڣ������ԤԼʱ��
   public List<OrderTime> queryOrderTime(int userId,String orderDate);
   //������ѯʦID
   public List<OrderTime> queryOrderTime(int userId);
   
   
   
   public List<OrderTime> queryOrderTime(OrderTime orderTime);
   
   
   //����ԤԼʱ�䣬��ѯʦID�����ڣ�ѡ�е�ʱ�������
   public boolean saveOrderTime(int userId,String orderDate,String[] orderHour);
   
}
