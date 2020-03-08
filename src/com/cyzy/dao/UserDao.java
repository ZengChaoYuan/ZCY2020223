package com.cyzy.dao;

import java.util.List;

import com.cyzy.bean.User;

public interface UserDao {//��̨�û���
    
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

	
	
	
	public int addUser(User user) throws Exception;
	
	// ��ѯ, ��ѯ�ж����û��Ѿ�ע��
	public List<User> queryUsers(User user);

	// ɾ��,ɾ���Ѿ�ע����û� 
	public int deleteUser(int userId);

	// ���±��е�����
	public int updateUser(User user);

	//û����
	public int createUserId();
	
}
