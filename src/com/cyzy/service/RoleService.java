package com.cyzy.service;

import java.util.List;

import com.cyzy.bean.Role;

public interface RoleService {

	//����
	public int addRole(Role role);
	
	//�޸�
	public int updateRole(Role role);
	
	//����ID����ȡһ��role����
	public Role getRoleById(int userId);
	
	//ɾ��
	public int deleteUser(int userId);
	
	//��ѯ
	public List<Role> queryRole(Role role);
}
