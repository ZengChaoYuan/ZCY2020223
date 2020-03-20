package com.cyzy.dao;

import java.util.List;
import java.util.Map;
import com.cyzy.bean.PreOrder;

public interface PreOrderDao {
	//客户评价咨询师
	public int assessConsulter(PreOrder preOrder);
	
	//咨询师诊断答复，修改预约表
	public int assessReply(PreOrder preOrder);
	
	// 咨询师查看自己的预约列表
	public int queryMyCustomerOrderCount(PreOrder preOrder);
	//public List<Map<String, Object>> queryMyCustomerOrders(PreOrder preOrder,String startTime, String endTime, int startIndex, int endIndex);
	public List<Map<String, Object>> queryMyCustomerOrders(PreOrder preOrder, int startIndex, int endIndex);
	// 客户查看自己的预约列表
	public int queryMyPreOrderCount(PreOrder preOrder);
	public List<Map<String, Object>> queryMyPreOrders(PreOrder preOrder, int startIndex, int endIndex);

	// 通过预约ID得到预约表的完整信息
	public PreOrder queryPreOrderById(int preorderId);

	// 客户查看详情
	public Map<String, Object> queryMyPreOrderDetail(int preorderId);

	// 客户（我要预约）
	public int addPreOrder(PreOrder preOrder) throws Exception;

	// 客户查询自己的预约列表
	public List<Map<String, Object>> queryMyPreOrderList(int customerId);

	// 咨询师查询所有客户的预约列表
	public List<Map<String, Object>> queryCustomerPreOrderList(int userId);

	// 管理员查看所有的预约列表
	public List<Map<String, Object>> queryAllPreOrderList();
    // 管理员查看所有的预约列表，分页
	public int queryAllPreOrderCount();
	public List<Map<String, Object>> queryAllPreOrders(int startIndex, int endIndex);
	// 查看详情
	public Map<String, Object> queryPreOrderDetailByIdStatus(int customerId, int orderStatus);

	// 查看咨询师档案:1.通过userId查询擅长领域的集合,2.通过userId查询用户表
	public List<Map<String, Object>> queryAreaListByUserId(int userId);

	// 通过userId查询咨询师信息
	public Map<String, Object> queryUserInfo(int userId);

	// 通过userId去查预约表中的客户名，客户评价时间，客户评价内容
	public List<Map<String, Object>> queryAssessInfo(int userId);

	// 查询该领域下心理咨询师的集合
	public List<Map<String, Object>> queryAllConsulterByAreaId(int areaId);

	// 管理员终止预约
	public int stopPreOrder(PreOrder preOrder);

}
