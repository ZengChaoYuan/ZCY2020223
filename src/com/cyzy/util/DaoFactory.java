package com.cyzy.util;

import java.util.HashMap;
import java.util.Map;

import com.cyzy.dao.GoodsDao;
import com.cyzy.dao.GoodsDaoImpl;
import com.cyzy.dao.MenuDao;
import com.cyzy.dao.MenuDaoImpl;
import com.cyzy.dao.RoleDao;
import com.cyzy.dao.RoleDaoImpl;
import com.cyzy.dao.UserDao;
import com.cyzy.dao.UserDaoImpl;

public class DaoFactory {
	 //����˽�б���
		private static Map<String,Object> config=new HashMap<String,Object>();
		//��̬��,ֻ��һ��ʵ��
		static {
			config.put(UserDao.class.getName(),new UserDaoImpl());//�û�dao
			config.put(RoleDao.class.getName(), new RoleDaoImpl());//��ɫdao
			config.put(GoodsDao.class.getName(), new GoodsDaoImpl());//��Ʒdao
			config.put(MenuDao.class.getName(),new MenuDaoImpl());//�˵�dao
		}
		//��̬������ȡʵ��
		public static Object getDaoImpl(String name) {
			return config.get(name);
		}
}
