package com.cyzy.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.cyzy.bean.Menu;
import com.cyzy.bean.Role;
import com.cyzy.bean.RoleMenu;
import com.cyzy.dao.MenuDao;
import com.cyzy.dao.RoleDao;
import com.cyzy.dao.RoleMenuDao;
import com.cyzy.util.DBUtil;
import com.cyzy.util.DaoFactory;

public class RoleServiceImpl implements RoleService {

	@Override
	public Role getRoleById(int roleId) {
		//�޸�Ȩ�޹���ǰ
		//���ý�ɫdao������в�ѯ
		RoleDao roleDao=(RoleDao)DaoFactory.getDaoImpl(RoleDao.class.getName());
		Role role=roleDao.getRoleById(roleId);
		//���ò˵�dao������в�ѯ����ѯ�����ݸ�role����
		MenuDao menuDao=(MenuDao)DaoFactory.getDaoImpl(MenuDao.class.getName());
		List<Menu> menuList=menuDao.queryMenuByRoleId(roleId);
		role.setMenuList(menuList);
		return role;
	}
	
	
	@Override
	public int addRole(String roleName, String[] menuArr) {
	  //��ɫ��:���ݽ�ɫId�����½�ɫ����
	  RoleDao roleDao=(RoleDao)DaoFactory.getDaoImpl(RoleDao.class.getName());
	  //��ɫ�˵���(�м��):�����������
	  RoleMenuDao roleMenuDao=(RoleMenuDao)DaoFactory.getDaoImpl(RoleMenuDao.class.getName());
	 
	  Connection conn=DBUtil.getConnection();
	   try {
		conn.setAutoCommit(false);
		int roleId=roleDao.createRoleId();//�Ȼ�ý�ɫid
		Role role=new Role(roleId,roleName);
		int resultRole=roleDao.addRole(role);//�����ɫ��
		
		
		
	} catch (SQLException e) {
		e.printStackTrace();
	}
		return 0;
	}
	
	
	@Override
	public int addRole(Role role) {
		
		return 0;
	}

	@Override
	public int updateRole(Role role) {
		RoleDao roleDao=(RoleDao)DaoFactory.getDaoImpl(RoleDao.class.getName());
		return roleDao.updateRole(role);
	}

	

	@Override
	public int deleteRole(int roleId) {
		RoleDao roleDao=(RoleDao)DaoFactory.getDaoImpl(RoleDao.class.getName());
		return roleDao.deleteRole(roleId);
	}
	
	@Override
	public List<Role> queryRole(Role role) {
		
		RoleDao roleDao=(RoleDao)DaoFactory.getDaoImpl(RoleDao.class.getName());
		return roleDao.queryRole(role);
	}


	

}
