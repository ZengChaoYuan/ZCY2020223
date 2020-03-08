package com.cyzy.dao;

import java.util.List;

import com.cyzy.bean.Role;

public interface RoleDao {//��ɫ�����ɾ�Ĳ�
	
	public int createRoleId();
	
	//û����
	public int addRole(Role role);
	//ɾ��
	public int deleteRole(int roleId);
	
	
	//��ѯ
	public List<Role> queryRole(Role role);
	
	//ͨ����ɫID����ȡ������ɫ����Ϣ
	public Role getRoleById(int roleId);
	//�޸�
	public int updateRole(Role role);
 
}
