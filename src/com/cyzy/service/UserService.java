package com.cyzy.service;

import java.util.List;
import java.util.Map;

import com.cyzy.bean.User;
import com.cyzy.util.Page;

public interface UserService {
	//�޸���ѯʦ���
	public int updateBalance(User user);
	
	//�����û�
	public Map<String,Object> querySingUserByUserId(int userId);
	
	// ����û����Ƿ��ظ�
	public User checkUserName(String userName);

	// ��¼
	public User login(String userName, String password);

	// ��ҳ��ѯ��������
	public Page<User> queryUserList(User user, int currentPageNum);

	// ͨ���û�ID����ú�̨�û���������Ϣ
	public User getUserById(int userId);

	// �޸��û�״̬:����/����
	public int updateUseStatus(User user);
	// �޸��û���״̬:�߼�ɾ��,ֻ�ı�״̬
	public int updateDeleteStatus(User user);
	// ��ʼ���ͻ�����
	public int resetPassword(User user);

	// ��ѯ
	public List<User> queryUsers(User user);

	// ����
	public int addUser(User user) throws Exception;

	// ɾ��
	public int deleteUser(int userId);

	// �޸�
	public int updateUser(User user);

}
