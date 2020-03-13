package com.cyzy.service;

import java.util.List;

import com.cyzy.bean.OrderTime;
import com.cyzy.dao.OrderTimeDao;
import com.cyzy.util.DaoFactory;

public class OrderTimeServiceImpl implements OrderTimeService {

	@Override
	public List<OrderTime> queryOrderTime(int userId, String orderDate) {
		OrderTimeDao orderTimeDao=(OrderTimeDao)DaoFactory.getDaoImpl(OrderTimeDao.class.getName());
		return orderTimeDao.queryOrderTime(userId, orderDate);
	}

	@Override
	public List<OrderTime> queryOrderTime(int userId) {
		OrderTimeDao orderTimeDao=(OrderTimeDao)DaoFactory.getDaoImpl(OrderTimeDao.class.getName());
		return orderTimeDao.queryOrderTime(userId);
	}

}
