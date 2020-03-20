package com.cyzy.service;

import java.util.List;
import java.util.Map;

import com.cyzy.bean.PreOrder;
import com.cyzy.util.Page;

public interface PreOrderService {
	  //客户评价咨询师
	  public int assessConsulter(PreOrder preOrder);
	
	  //咨询师诊断答复，修改预约表
	  public int assessReply(PreOrder preOrder);
	
	  //咨询师查看预约自己的客户,分页
	  //public Page<Map<String,Object>> queryMyCustomerOrders(PreOrder preOrder,String startTime, String endTime, int currentPageNum);
	  //客户查看自己的账户,分页
	  public Page<Map<String,Object>> queryMyCustomerOrders(PreOrder preOrder, int currentPageNum);
	
	  //客户查看详情
	  public Map<String,Object> queryMyPreOrderDetail(int preorderId);
	
	  //客户（我要预约）
	  public int addPreOrder(PreOrder preOrder) throws Exception;
	
	  //管理员终止预约
	  public PreOrder queryPreOrderById(int preorderId);
	  public int stopPreOrder(PreOrder preOrder);
	
	  //客户查询自己的预约列表
	  public List<Map<String,Object>> queryMyPreOrderList(int customerId);
	  //客户查看自己的账户,分页
	  public Page<Map<String,Object>> queryMyPreOrders(PreOrder preOrder, int currentPageNum);
	  
	  //咨询师查询所有客户的预约列表
	  public List<Map<String,Object>> queryCustomerPreOrderList(int userId);
	  
	  //管理员查看所有的预约列表
	  public List<Map<String,Object>> queryAllPreOrderList();
	  //分页
	  public Page<Map<String,Object>> queryAllPreOrders(int currentPageNum);
	  
	  //查看详情
	  public Map<String,Object> queryPreOrderDetailByIdStatus(int customerId,int orderStatus);
	  
	  //查看咨询师档案:
	  //1.通过userId查询擅长领域的集合
	  public List<Map<String,Object>> queryAreaListByUserId(int userId);
	  	  
	  //2.通过userId查询咨询师信息
	  public Map<String,Object> queryUserInfo(int userId);
	  
	  //通过userId去查预约表中的客户名，客户评价时间，客户评价内容
	  public List<Map<String,Object>> queryAssessInfo(int userId);
	  
	  //查询该领域下心理咨询师的集合
	  public List<Map<String,Object>> queryAllConsulterByAreaId(int areaId);
	  
	  
	  
}
