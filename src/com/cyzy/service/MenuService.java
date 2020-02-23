package com.cyzy.service;

import java.util.List;

import com.cyzy.bean.Menu;

public interface MenuService {
 
	public List<Menu> queryMenuByuserName(String userName);
}
