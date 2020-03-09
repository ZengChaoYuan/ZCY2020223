package com.cyzy.service;

import com.cyzy.bean.RoleMenu;
import com.cyzy.dao.RoleMenuDao;
import com.cyzy.util.DaoFactory;

public class RoleMenuServiceImpl implements RoleMenuService {

	@Override
	public int deleteRoleMenu(int roleId) {
		RoleMenuDao roleMenuDao=(RoleMenuDao)DaoFactory.getDaoImpl(RoleMenuDao.class.getName());
		return roleMenuDao.deleteRoleMenu(roleId);
	}

	@Override
	public int addRoleMenu(int roleId, int[] menuIds) throws Exception {
		RoleMenuDao roleMenuDao=(RoleMenuDao)DaoFactory.getDaoImpl(RoleMenuDao.class.getName());
		return roleMenuDao.addRoleMenu(roleId, menuIds);
	}

	@Override
	public int addRoleMenu(RoleMenu roleMenu) {
		RoleMenuDao roleMenuDao=(RoleMenuDao)DaoFactory.getDaoImpl(RoleMenuDao.class.getName());
		return roleMenuDao.addRoleMenu(roleMenu);
	}

}
