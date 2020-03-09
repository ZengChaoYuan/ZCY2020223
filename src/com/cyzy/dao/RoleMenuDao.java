package com.cyzy.dao;

import com.cyzy.bean.RoleMenu;

public interface RoleMenuDao {
  public int addRoleMenu(RoleMenu roleMenu);
  
 
  
  public int addRoleMenu(int roleId);
  
  //删除角色菜单中间表
  public int deleteRoleMenu(int roleId);
  
  //增加角色菜单中间表
  public int addRoleMenu(int roleId, int[] menuIds) throws Exception;
  
  
}
