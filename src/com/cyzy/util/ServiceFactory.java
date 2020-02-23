package com.cyzy.util;

import java.util.HashMap;
import java.util.Map;

import com.cyzy.service.GoodsService;
import com.cyzy.service.GoodsServiceImpl;
import com.cyzy.service.MenuService;
import com.cyzy.service.MenuServiceImpl;
import com.cyzy.service.RoleService;
import com.cyzy.service.RoleServiceImpl;
import com.cyzy.service.UserService;
import com.cyzy.service.UserServiceImpl;

public class ServiceFactory {
	private static Map<String,Object> config=new HashMap<String,Object>();
    static {
    	config.put(UserService.class.getName(), new UserServiceImpl());//�û�service
    	config.put(RoleService.class.getName(), new RoleServiceImpl());//��ɫservice
    	config.put(GoodsService.class.getName(),new GoodsServiceImpl());//��Ʒservice
    	config.put(MenuService.class.getName(),new MenuServiceImpl());//�˵�service
    }
    public static Object getServiceImpl(String name) {
    	return config.get(name);
    }
}


