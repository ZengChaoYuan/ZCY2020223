package com.cyzy.service;

import java.util.List;

import com.cyzy.bean.Menu;

public interface MenuService {
    //���û�������ѯ��ɫ��ӵ�еĲ˵�
	public List<Menu> queryMenuByuserName(String userName);
	
	//��ѯ�˵��б�
	public List<Menu> queryAllMenu();
	
	//����ɫid����ѯ��ɫ��ӵ�еĲ˵�
	public List<Menu> queryMenuByRoleId(int roleId);
	
}
