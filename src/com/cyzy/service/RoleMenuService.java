package com.cyzy.service;

import com.cyzy.bean.RoleMenu;

public interface RoleMenuService {
	//ɾ����ɫ�˵��м��
	public int deleteRoleMenu(int roleId);
	
	//���ӽ�ɫ�˵��м��
	public int addRoleMenu(int roleId, int[] menuIds) throws Exception;
	
	//����
	public int addRoleMenu(RoleMenu roleMenu);
}
