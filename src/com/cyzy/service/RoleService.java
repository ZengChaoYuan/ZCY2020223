package com.cyzy.service;

import java.util.List;

import com.cyzy.bean.Role;

public interface RoleService {

	//�ⲽ��������Ȩ�޹���
	public Role getRoleById(int roleId);
	
	
	//��ѯ
	public List<Role> queryRole(Role role);
	
	//���ӽ�ɫ,���ӽ�ɫ���ƺͲ˵�����
	public int addRole(String roleName,String[] menuArr);	
	
	//����
	public int addRole(Role role);
	
	//�޸�
	public int updateRole(Role role);
	
	//ɾ��
	public int deleteRole(int roleId);
		
	
	
	
	
    

}
