package com.cyzy.service;

import java.util.List;

import com.cyzy.bean.Menu;
import com.cyzy.dao.MenuDao;
import com.cyzy.util.DaoFactory;

public class MenuServiceImpl implements MenuService {

	@Override
	public List<Menu> queryMenuByuserName(String userName) {
		//����ת��,ʵ����,�����ýӿ�����
        MenuDao menuDao=(MenuDao)DaoFactory.getDaoImpl(MenuDao.class.getName());
		return menuDao.queryMenuByuserName(userName);
	}

	@Override
	public List<Menu> queryAllMenu() {
		 MenuDao menuDao=(MenuDao)DaoFactory.getDaoImpl(MenuDao.class.getName());
		return menuDao.queryAllMenu();
	}

	@Override
	public List<Menu> queryMenuByRoleId(int roleId) {
		MenuDao menuDao=(MenuDao)DaoFactory.getDaoImpl(MenuDao.class.getName());
		return menuDao.queryMenuByRoleId(roleId);
	}

}
