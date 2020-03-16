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
    	config.put(DictService.class.getName(),new DictServiceImpl());//�ֵ��
    	config.put(UserService.class.getName(), new UserServiceImpl());//��̨�û�service
    	config.put(CustomerService.class.getName(), new CustomerServiceImpl());//ǰ̨�û�service
    	config.put(ParamService.class.getName(), new ParamServiceImpl());//����service
    	config.put(RoleService.class.getName(), new RoleServiceImpl());//��ɫservice
    	config.put(RoleMenuService.class.getName(), new RoleMenuServiceImpl());//��ɫ�˵�service
    	config.put(TitleService.class.getName(), new TitleServiceImpl());//��Ŀservice
    	config.put(ItemService.class.getName(), new ItemServiceImpl());//ѡ��service
    	config.put(OrderTimeService.class.getName(), new OrderTimeServiceImpl());//ԤԼʱ������service
    	config.put(AreaService.class.getName(), new AreaServiceImpl());//����service
    	config.put(MyAccountService.class.getName(), new MyAccountServiceImpl());//�ҵ��˻�service
    	config.put(AssReportService.class.getName(), new AssReportServiceImpl());//��������
    	config.put(StandService.class.getName(), new StandServiceImpl());//��׼
    	config.put(PreOrderService.class.getName(), new PreOrderServiceImpl());//ԤԼ��
    	config.put(GoodsService.class.getName(),new GoodsServiceImpl());//��Ʒservice
    	config.put(MenuService.class.getName(),new MenuServiceImpl());//�˵�service
    	config.put(LogService.class.getName(),new LogServiceImpl());//��־service
    }
    public static Object getServiceImpl(String name) {
    	return config.get(name);
    }
}


