package com.cyzy.dao;

import java.util.List;
import java.util.Map;
import com.cyzy.bean.PreOrder;

public interface PreOrderDao {
	//�ͻ�������ѯʦ
	public int assessConsulter(PreOrder preOrder);
	
	//��ѯʦ��ϴ𸴣��޸�ԤԼ��
	public int assessReply(PreOrder preOrder);
	
	// ��ѯʦ�鿴�Լ���ԤԼ�б�
	public int queryMyCustomerOrderCount(PreOrder preOrder);
	//public List<Map<String, Object>> queryMyCustomerOrders(PreOrder preOrder,String startTime, String endTime, int startIndex, int endIndex);
	public List<Map<String, Object>> queryMyCustomerOrders(PreOrder preOrder, int startIndex, int endIndex);
	// �ͻ��鿴�Լ���ԤԼ�б�
	public int queryMyPreOrderCount(PreOrder preOrder);
	public List<Map<String, Object>> queryMyPreOrders(PreOrder preOrder, int startIndex, int endIndex);

	// ͨ��ԤԼID�õ�ԤԼ���������Ϣ
	public PreOrder queryPreOrderById(int preorderId);

	// �ͻ��鿴����
	public Map<String, Object> queryMyPreOrderDetail(int preorderId);

	// �ͻ�����ҪԤԼ��
	public int addPreOrder(PreOrder preOrder) throws Exception;

	// �ͻ���ѯ�Լ���ԤԼ�б�
	public List<Map<String, Object>> queryMyPreOrderList(int customerId);

	// ��ѯʦ��ѯ���пͻ���ԤԼ�б�
	public List<Map<String, Object>> queryCustomerPreOrderList(int userId);

	// ����Ա�鿴���е�ԤԼ�б�
	public List<Map<String, Object>> queryAllPreOrderList();
    // ����Ա�鿴���е�ԤԼ�б���ҳ
	public int queryAllPreOrderCount();
	public List<Map<String, Object>> queryAllPreOrders(int startIndex, int endIndex);
	// �鿴����
	public Map<String, Object> queryPreOrderDetailByIdStatus(int customerId, int orderStatus);

	// �鿴��ѯʦ����:1.ͨ��userId��ѯ�ó�����ļ���,2.ͨ��userId��ѯ�û���
	public List<Map<String, Object>> queryAreaListByUserId(int userId);

	// ͨ��userId��ѯ��ѯʦ��Ϣ
	public Map<String, Object> queryUserInfo(int userId);

	// ͨ��userIdȥ��ԤԼ���еĿͻ������ͻ�����ʱ�䣬�ͻ���������
	public List<Map<String, Object>> queryAssessInfo(int userId);

	// ��ѯ��������������ѯʦ�ļ���
	public List<Map<String, Object>> queryAllConsulterByAreaId(int areaId);

	// ����Ա��ֹԤԼ
	public int stopPreOrder(PreOrder preOrder);

}
