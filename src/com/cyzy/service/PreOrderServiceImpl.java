package com.cyzy.service;

import java.util.List;
import java.util.Map;

import com.cyzy.bean.PreOrder;
import com.cyzy.bean.User;
import com.cyzy.dao.PreOrderDao;
import com.cyzy.dao.UserDao;
import com.cyzy.util.DaoFactory;
import com.cyzy.util.Page;

public class PreOrderServiceImpl implements PreOrderService {

	@Override
	public List<Map<String, Object>> queryMyPreOrderList(int customerId) {
		PreOrderDao preOrderDao=(PreOrderDao)DaoFactory.getDaoImpl(PreOrderDao.class.getName());
		return preOrderDao.queryMyPreOrderList(customerId);
	}

	@Override
	public Map<String, Object> queryPreOrderDetailByIdStatus(int customerId, int orderStatus) {
		PreOrderDao preOrderDao=(PreOrderDao)DaoFactory.getDaoImpl(PreOrderDao.class.getName());
		return preOrderDao.queryPreOrderDetailByIdStatus(customerId, orderStatus);
	}

	@Override
	public List<Map<String, Object>> queryAreaListByUserId(int userId) {
		PreOrderDao preOrderDao=(PreOrderDao)DaoFactory.getDaoImpl(PreOrderDao.class.getName());
		return preOrderDao.queryAreaListByUserId(userId);
	}

	@Override
	public Map<String, Object> queryUserInfo(int userId) {
		PreOrderDao preOrderDao=(PreOrderDao)DaoFactory.getDaoImpl(PreOrderDao.class.getName());
		return preOrderDao.queryUserInfo(userId);
	}

	@Override
	public List<Map<String, Object>> queryAllConsulterByAreaId(int areaId) {
		PreOrderDao preOrderDao=(PreOrderDao)DaoFactory.getDaoImpl(PreOrderDao.class.getName());
		return preOrderDao.queryAllConsulterByAreaId(areaId);
	}

	@Override
	public List<Map<String, Object>> queryCustomerPreOrderList(int userId) {
		PreOrderDao preOrderDao=(PreOrderDao)DaoFactory.getDaoImpl(PreOrderDao.class.getName());
		return preOrderDao.queryCustomerPreOrderList(userId);
	}

	@Override
	public List<Map<String, Object>> queryAllPreOrderList() {
		PreOrderDao preOrderDao=(PreOrderDao)DaoFactory.getDaoImpl(PreOrderDao.class.getName());
		return preOrderDao.queryAllPreOrderList();
	}

	@Override
	public int stopPreOrder(PreOrder preOrder) {
		PreOrderDao preOrderDao=(PreOrderDao)DaoFactory.getDaoImpl(PreOrderDao.class.getName());
		return preOrderDao.stopPreOrder(preOrder);
	}

	@Override
	public PreOrder queryPreOrderById(int preorderId) {
		PreOrderDao preOrderDao=(PreOrderDao)DaoFactory.getDaoImpl(PreOrderDao.class.getName());
		return preOrderDao.queryPreOrderById(preorderId);
	}

	@Override
	public int addPreOrder(PreOrder preOrder) throws Exception {
		PreOrderDao preOrderDao=(PreOrderDao)DaoFactory.getDaoImpl(PreOrderDao.class.getName());
		return preOrderDao.addPreOrder(preOrder);
	}

	@Override
	public Page<Map<String, Object>> queryMyPreOrders(PreOrder preOrder, int currentPageNum) {
		PreOrderDao preOrderDao=(PreOrderDao)DaoFactory.getDaoImpl(PreOrderDao.class.getName());
		int totalRecordsNum=preOrderDao.queryMyPreOrderCount(preOrder);
		Page<Map<String,Object>> page=new Page<Map<String,Object>>(currentPageNum,totalRecordsNum,5);
		List<Map<String,Object>> preOrderCounts=preOrderDao.queryMyPreOrders(preOrder, page.getStartIndex(),page.getEndIndex());
		page.setRecords(preOrderCounts);
		return page;
	}
	
	@Override
	public Page<Map<String, Object>> queryMyCustomerOrders(PreOrder preOrder, int currentPageNum) {
		PreOrderDao preOrderDao=(PreOrderDao)DaoFactory.getDaoImpl(PreOrderDao.class.getName());
		int totalRecordsNum=preOrderDao.queryMyCustomerOrderCount(preOrder);
		Page<Map<String,Object>> page=new Page<Map<String,Object>>(currentPageNum,totalRecordsNum,5);
		List<Map<String,Object>> myCustomerOrderCounts=preOrderDao.queryMyCustomerOrders(preOrder, page.getStartIndex(),page.getEndIndex());
		page.setRecords(myCustomerOrderCounts);
		return page;
	}

	@Override
	public Page<Map<String, Object>> queryAllPreOrders(int currentPageNum) {
		PreOrderDao preOrderDao=(PreOrderDao)DaoFactory.getDaoImpl(PreOrderDao.class.getName());
		int totalRecordsNum=preOrderDao.queryAllPreOrderCount();
		Page<Map<String,Object>> page=new Page<Map<String,Object>>(currentPageNum,totalRecordsNum,5);
		List<Map<String,Object>> allPreOrderList=preOrderDao.queryAllPreOrders(page.getStartIndex(),page.getEndIndex());
		page.setRecords(allPreOrderList);
		return page;
	}
	@Override
	public Map<String, Object> queryMyPreOrderDetail(int preorderId) {
		PreOrderDao preOrderDao=(PreOrderDao)DaoFactory.getDaoImpl(PreOrderDao.class.getName());
		return preOrderDao.queryMyPreOrderDetail(preorderId);
	}

	@Override
	public List<Map<String, Object>> queryAssessInfo(int userId) {
		PreOrderDao preOrderDao=(PreOrderDao)DaoFactory.getDaoImpl(PreOrderDao.class.getName());
		return preOrderDao.queryAssessInfo(userId);
	}

	@Override
	public int assessReply(PreOrder preOrder) {
		PreOrderDao preOrderDao=(PreOrderDao)DaoFactory.getDaoImpl(PreOrderDao.class.getName());
		return preOrderDao.assessReply(preOrder);
	}

	

	

//	@Override
//	public Page<Map<String, Object>> queryMyCustomerOrders(PreOrder preOrder, String startTime, String endTime,
//			int currentPageNum) {
//		PreOrderDao preOrderDao=(PreOrderDao)DaoFactory.getDaoImpl(PreOrderDao.class.getName());
//		int totalRecordsNum=preOrderDao.queryMyCustomerOrderCount(preOrder);
//		Page<Map<String,Object>> page=new Page<Map<String,Object>>(currentPageNum,totalRecordsNum,5);
//		List<Map<String,Object>> myCustomerOrders=preOrderDao.queryMyCustomerOrders(preOrder, startTime, endTime, page.getStartIndex(),page.getEndIndex());
//		page.setRecords(myCustomerOrders);
//		return page;
//	}


}
