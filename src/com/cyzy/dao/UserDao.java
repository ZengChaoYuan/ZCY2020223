package com.cyzy.dao;

import java.util.List;

import com.cyzy.bean.User;

public interface UserDao {

	// �����û�, ע�Ṧ��
	public int addUser(User user);

	// ��ѯ, ��ѯ�ж����û��Ѿ�ע��
	public List<User> queryUsers(User user);

	// ɾ��,ɾ���Ѿ�ע����û� 
	public int deleteUser(int userId);

	// ���±��е�����
	public int updateUser(User user);
	//�����û�Id���޸��û�
	public User updateUser(int userId);
    
	public User loginUser(User user);
	
	//�����û�Id���޸��û�,�鿴�û�����
	public User getUserById(int userId);
	
	//��¼
	public User login(String userName,String password);
	
	//û����
	public int createUserId();
	
}
