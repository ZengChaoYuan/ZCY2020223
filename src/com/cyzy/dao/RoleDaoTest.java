package com.cyzy.dao;

import com.cyzy.bean.Role;
import com.cyzy.util.DaoFactory;

public class RoleDaoTest {
	public static void main(String[] args) {
      //�����ɫ��
		
	  
	  RoleDao roleDao=(RoleDao)DaoFactory.getDaoImpl(RoleDao.class.getName());
	  int roleId=roleDao.createRoleId();
	  Role role=new Role(roleId,"��������Ա");
	  int result=roleDao.addRole(role);
	  System.out.println(result>0?"��ӳɹ�":"���ʧ��");
	  
	  
	}
}
