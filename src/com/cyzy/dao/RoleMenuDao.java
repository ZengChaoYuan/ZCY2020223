package com.cyzy.dao;

import com.cyzy.bean.RoleMenu;

public interface RoleMenuDao {
  public int addRoleMenu(RoleMenu roleMenu);
  
 
  
  public int addRoleMenu(int roleId);
  
  //ɾ����ɫ�˵��м��
  public int deleteRoleMenu(int roleId);
  
  //���ӽ�ɫ�˵��м��
  public int addRoleMenu(int roleId, int[] menuIds) throws Exception;
  
  
}
