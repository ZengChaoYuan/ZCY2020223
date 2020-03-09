package com.cyzy.dao;

import com.cyzy.bean.Role;
import com.cyzy.util.DaoFactory;

public class RoleDaoTest {
	public static void main(String[] args) {
      //插入角色表
		
	  
	  RoleDao roleDao=(RoleDao)DaoFactory.getDaoImpl(RoleDao.class.getName());
	  int roleId=roleDao.createRoleId();
	  Role role=new Role(roleId,"超级管理员");
	  int result=roleDao.addRole(role);
	  System.out.println(result>0?"添加成功":"添加失败");
	  
	  
	}
}
