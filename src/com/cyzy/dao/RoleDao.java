package com.cyzy.dao;

import java.util.List;

import com.cyzy.bean.Role;

public interface RoleDao {//��ɫ�����ɾ�Ĳ�
	//����
	public int addRole(Role role);
	//ɾ��
	public int deleteRole(int roleId);
	//�޸�
	public int updateRole(Role role);
	
	//��ѯ
	public List<Role> queryRole(Role role);
 
}
