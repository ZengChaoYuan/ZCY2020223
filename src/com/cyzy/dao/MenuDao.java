package com.cyzy.dao;

import java.util.List;

import com.cyzy.bean.Menu;

public interface MenuDao {
  //���û�������ѯ�û���ӵ�еĲ˵�
  public List<Menu> queryMenuByuserName(String userName);
  
  //��ѯ���еĲ˵��б�
  public List<Menu> queryAllMenu();
  
  //����ɫid����ѯ��ɫ��ӵ�еĲ˵�
  public List<Menu> queryMenuByRoleId(int roleId);
  
}
