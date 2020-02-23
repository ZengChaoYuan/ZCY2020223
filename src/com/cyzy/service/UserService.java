package com.cyzy.service;

import java.util.List;

import com.cyzy.bean.User;

public interface UserService {
	
  
	//����ID����ȡһ��user����
	public User getUserById(int userId);
	//��ѯ
	public List<User> queryUsers(User user);
	//����
	public int addUser(User user);
	//ɾ��
	public int deleteUser(int userId);
	//�޸�
	public int updateUser(User user);
	
	//��¼
	public User login(String userName,String password);
		
}
