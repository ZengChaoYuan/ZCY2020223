package com.cyzy.service;

import java.util.List;
import java.util.Map;

import com.cyzy.bean.PreOrder;
import com.cyzy.util.Page;

public interface PreOrderService {
	  //�ͻ�������ѯʦ
	  public int assessConsulter(PreOrder preOrder);
	
	  //��ѯʦ��ϴ𸴣��޸�ԤԼ��
	  public int assessReply(PreOrder preOrder);
	
	  //��ѯʦ�鿴ԤԼ�Լ��Ŀͻ�,��ҳ
	  //public Page<Map<String,Object>> queryMyCustomerOrders(PreOrder preOrder,String startTime, String endTime, int currentPageNum);
	  //�ͻ��鿴�Լ����˻�,��ҳ
	  public Page<Map<String,Object>> queryMyCustomerOrders(PreOrder preOrder, int currentPageNum);
	
	  //�ͻ��鿴����
	  public Map<String,Object> queryMyPreOrderDetail(int preorderId);
	
	  //�ͻ�����ҪԤԼ��
	  public int addPreOrder(PreOrder preOrder) throws Exception;
	
	  //����Ա��ֹԤԼ
	  public PreOrder queryPreOrderById(int preorderId);
	  public int stopPreOrder(PreOrder preOrder);
	
	  //�ͻ���ѯ�Լ���ԤԼ�б�
	  public List<Map<String,Object>> queryMyPreOrderList(int customerId);
	  //�ͻ��鿴�Լ����˻�,��ҳ
	  public Page<Map<String,Object>> queryMyPreOrders(PreOrder preOrder, int currentPageNum);
	  
	  //��ѯʦ��ѯ���пͻ���ԤԼ�б�
	  public List<Map<String,Object>> queryCustomerPreOrderList(int userId);
	  
	  //����Ա�鿴���е�ԤԼ�б�
	  public List<Map<String,Object>> queryAllPreOrderList();
	  //��ҳ
	  public Page<Map<String,Object>> queryAllPreOrders(int currentPageNum);
	  
	  //�鿴����
	  public Map<String,Object> queryPreOrderDetailByIdStatus(int customerId,int orderStatus);
	  
	  //�鿴��ѯʦ����:
	  //1.ͨ��userId��ѯ�ó�����ļ���
	  public List<Map<String,Object>> queryAreaListByUserId(int userId);
	  	  
	  //2.ͨ��userId��ѯ��ѯʦ��Ϣ
	  public Map<String,Object> queryUserInfo(int userId);
	  
	  //ͨ��userIdȥ��ԤԼ���еĿͻ������ͻ�����ʱ�䣬�ͻ���������
	  public List<Map<String,Object>> queryAssessInfo(int userId);
	  
	  //��ѯ��������������ѯʦ�ļ���
	  public List<Map<String,Object>> queryAllConsulterByAreaId(int areaId);
	  
	  
	  
}
