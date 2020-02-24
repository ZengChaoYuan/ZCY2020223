package com.cyzy.service;

import java.util.List;

import com.cyzy.bean.User;
import com.cyzy.util.Page;

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
	
	//����page����:�������ҳ��չʾ�� 1.����,2.�ͷ�ҳ��ص�����
	//����:����2��,1�ǲ�ѯ����,2�ǲ�ѯҳ��
	public Page<User> queryUsers(User user,int currentPageNum);
		
}
