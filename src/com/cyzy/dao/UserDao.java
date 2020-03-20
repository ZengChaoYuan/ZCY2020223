package com.cyzy.dao;

import java.util.List;
import java.util.Map;
import com.cyzy.bean.OrderCount;
import com.cyzy.bean.PreOrderCount;
import com.cyzy.bean.User;

public interface UserDao {//��̨�û���
	//�޸���ѯʦ���
	public int updateBalance(User user);
	
    //�����û�
	public Map<String,Object> querySingUserByUserId(int userId);
	
	//��¼
	public User login(String userName,String password);
	
	//����û����Ƿ��ظ�
	public User checkUserName(String userName);
	
	//1.��ѯ�ж��������������ļ�¼: �ܼ�¼��
	public int queryCount(User user);
    //2.��ҳ��ѯ�û���������
	public List<User> queryUserList(User user,int startIndex,int endIndex);
	
	//ͨ���û�Id���޸��û�,�鿴�û�����
	public User getUserById(int userId);
	
	//�޸��û�״̬:����/����
	public int updateUseStatus(User user);	
	//�޸��û���״̬:�߼�ɾ��,ֻ�ı�״̬
	public int updateDeleteStatus(User user);
	//��ʼ���ͻ�����
	public int resetPassword(User user);

	//����Ա�����̨�û�
	public int addUser(User user) throws Exception;
	
	// ��ѯ, ��ѯ�ж����û��Ѿ�ע��
	public List<User> queryUsers(User user);

	// ɾ��,ɾ���Ѿ�ע����û� 
	public int deleteUser(int userId);

	// ���±��е�����
	public int updateUser(User user);

	//�û�ID
	public int createUserId();
	//������ɫΪ��ѯʦ����
	public int addUserArea(int userId,int [] areaIds) throws Exception;
	
	// ����ѯʦ�û���ѯ��־:��ʼʱ��ͽ���ʱ��
	public List<OrderCount> queryAllOrderCountByUser(String startTime, String endTime);
	
	//��ѯʦȷ��ԤԼ�󣬰����ݲ��뵽ԤԼ��������
	public int addOrderCount(PreOrderCount preOrderCount) throws Exception;
	
	
}
