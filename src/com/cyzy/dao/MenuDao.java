package com.cyzy.dao;

import java.util.List;

import com.cyzy.bean.Menu;

public interface MenuDao {
	
  public List<Menu> queryMenuByuserName(String userName);
  
}
