package com.cyzy.dao;

import java.util.List;

import com.cyzy.bean.Menu;

public interface MenuDao {
  //靠用户名来查询用户所拥有的菜单
  public List<Menu> queryMenuByuserName(String userName);
  
  //查询所有的菜单列表
  public List<Menu> queryAllMenu();
  
  //靠角色id来查询角色所拥有的菜单
  public List<Menu> queryMenuByRoleId(int roleId);
  
}
