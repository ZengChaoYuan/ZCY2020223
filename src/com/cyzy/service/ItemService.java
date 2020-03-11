package com.cyzy.service;

import java.util.List;

import com.cyzy.bean.Item;

public interface ItemService {
	  //查询所有的选项
	  public List<Item> queryItems(int titleId);
	  
	  //通过题目ID得到选项
	  public Item getItemBytitleId(int titleId);
	  
	  public int deleteItemBytitleId(int titleId);
	  
	  public int addItem(Item item);
	  
	  //增加:包含数组
	  public int addItem(int titleId,String[] itemNames,int[] scopes) throws Exception;
}
