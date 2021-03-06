package com.cyzy.util;

import java.util.HashMap;
import java.util.Map;

import com.cyzy.dao.AreaDao;
import com.cyzy.dao.AreaDaoImpl;
import com.cyzy.dao.AssReportDao;
import com.cyzy.dao.AssReportDaoImpl;
import com.cyzy.dao.CustomerDao;
import com.cyzy.dao.CustomerDaoImpl;
import com.cyzy.dao.DictDao;
import com.cyzy.dao.DictDaoImpl;
import com.cyzy.dao.GoodsDao;
import com.cyzy.dao.GoodsDaoImpl;
import com.cyzy.dao.ItemDao;
import com.cyzy.dao.ItemDaoImpl;
import com.cyzy.dao.LogDao;
import com.cyzy.dao.LogDaoImpl;
import com.cyzy.dao.MenuDao;
import com.cyzy.dao.MenuDaoImpl;
import com.cyzy.dao.MyAccountDao;
import com.cyzy.dao.MyAccountDaoImpl;
import com.cyzy.dao.OrderTimeDao;
import com.cyzy.dao.OrderTimeDaoImpl;
import com.cyzy.dao.ParamDao;
import com.cyzy.dao.ParamDaoImpl;
import com.cyzy.dao.PreOrderDao;
import com.cyzy.dao.PreOrderDaoImpl;
import com.cyzy.dao.RoleDao;
import com.cyzy.dao.RoleDaoImpl;
import com.cyzy.dao.RoleMenuDao;
import com.cyzy.dao.RoleMenuDaoImpl;
import com.cyzy.dao.StandDao;
import com.cyzy.dao.StandDaoImpl;
import com.cyzy.dao.TitleDao;
import com.cyzy.dao.TitleDaoImpl;
import com.cyzy.dao.UserDao;
import com.cyzy.dao.UserDaoImpl;

public class DaoFactory {
	 //声明私有变量
		private static Map<String,Object> config=new HashMap<String,Object>();
		//静态块,只有一个实例
		static {
			config.put(DictDao.class.getName(),new DictDaoImpl());//字典表
			config.put(UserDao.class.getName(),new UserDaoImpl());//后台用户dao
			config.put(CustomerDao.class.getName(),new CustomerDaoImpl());//前台用户dao
			config.put(ParamDao.class.getName(),new ParamDaoImpl());//参数表dao
			config.put(RoleDao.class.getName(), new RoleDaoImpl());//角色dao
			config.put(RoleMenuDao.class.getName(),new RoleMenuDaoImpl());//角色菜单中间表
			config.put(TitleDao.class.getName(),new TitleDaoImpl());//题目表	
			config.put(ItemDao.class.getName(),new ItemDaoImpl());//选项表
			config.put(OrderTimeDao.class.getName(), new OrderTimeDaoImpl());//预约时间设置表
			config.put(AreaDao.class.getName(),new AreaDaoImpl());//领域表
			config.put(MyAccountDao.class.getName(), new MyAccountDaoImpl());//我的账户
			config.put(AssReportDao.class.getName(), new AssReportDaoImpl());//评估报告表
			config.put(StandDao.class.getName(),new StandDaoImpl());//标准表
			config.put(PreOrderDao.class.getName(), new PreOrderDaoImpl());//预约表
			config.put(GoodsDao.class.getName(), new GoodsDaoImpl());//商品dao
			config.put(MenuDao.class.getName(),new MenuDaoImpl());//菜单dao
			config.put(LogDao.class.getName(),new LogDaoImpl());//日志dao
		}
		//静态方法获取实例
		public static Object getDaoImpl(String name) {
			return config.get(name);
		}
}
