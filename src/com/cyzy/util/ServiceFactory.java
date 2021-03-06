package com.cyzy.util;

import java.util.HashMap;
import java.util.Map;

import com.cyzy.bean.Area;
import com.cyzy.dao.DictDao;
import com.cyzy.dao.DictDaoImpl;
import com.cyzy.service.AreaService;
import com.cyzy.service.AreaServiceImpl;
import com.cyzy.service.AssReportService;
import com.cyzy.service.AssReportServiceImpl;
import com.cyzy.service.CustomerService;
import com.cyzy.service.CustomerServiceImpl;
import com.cyzy.service.DictService;
import com.cyzy.service.DictServiceImpl;
import com.cyzy.service.GoodsService;
import com.cyzy.service.GoodsServiceImpl;
import com.cyzy.service.ItemService;
import com.cyzy.service.ItemServiceImpl;
import com.cyzy.service.LogService;
import com.cyzy.service.LogServiceImpl;
import com.cyzy.service.MenuService;
import com.cyzy.service.MenuServiceImpl;
import com.cyzy.service.MyAccountService;
import com.cyzy.service.MyAccountServiceImpl;
import com.cyzy.service.OrderTimeService;
import com.cyzy.service.OrderTimeServiceImpl;
import com.cyzy.service.ParamService;
import com.cyzy.service.ParamServiceImpl;
import com.cyzy.service.PreOrderService;
import com.cyzy.service.PreOrderServiceImpl;
import com.cyzy.service.RoleMenuService;
import com.cyzy.service.RoleMenuServiceImpl;
import com.cyzy.service.RoleService;
import com.cyzy.service.RoleServiceImpl;
import com.cyzy.service.StandService;
import com.cyzy.service.StandServiceImpl;
import com.cyzy.service.TitleService;
import com.cyzy.service.TitleServiceImpl;
import com.cyzy.service.UserService;
import com.cyzy.service.UserServiceImpl;

public class ServiceFactory {
	private static Map<String,Object> config=new HashMap<String,Object>();
    static {
    	config.put(DictService.class.getName(),new DictServiceImpl());//字典表
    	config.put(UserService.class.getName(), new UserServiceImpl());//后台用户service
    	config.put(CustomerService.class.getName(), new CustomerServiceImpl());//前台用户service
    	config.put(ParamService.class.getName(), new ParamServiceImpl());//参数service
    	config.put(RoleService.class.getName(), new RoleServiceImpl());//角色service
    	config.put(RoleMenuService.class.getName(), new RoleMenuServiceImpl());//角色菜单service
    	config.put(TitleService.class.getName(), new TitleServiceImpl());//题目service
    	config.put(ItemService.class.getName(), new ItemServiceImpl());//选项service
    	config.put(OrderTimeService.class.getName(), new OrderTimeServiceImpl());//预约时间设置service
    	config.put(AreaService.class.getName(), new AreaServiceImpl());//领域service
    	config.put(MyAccountService.class.getName(), new MyAccountServiceImpl());//我的账户service
    	config.put(AssReportService.class.getName(), new AssReportServiceImpl());//评估报告
    	config.put(StandService.class.getName(), new StandServiceImpl());//标准
    	config.put(PreOrderService.class.getName(), new PreOrderServiceImpl());//预约表
    	config.put(GoodsService.class.getName(),new GoodsServiceImpl());//商品service
    	config.put(MenuService.class.getName(),new MenuServiceImpl());//菜单service
    	config.put(LogService.class.getName(),new LogServiceImpl());//日志service
    }
    public static Object getServiceImpl(String name) {
    	return config.get(name);
    }
}


