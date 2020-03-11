package com.cyzy.dao;

import java.util.List;

import com.cyzy.bean.Item;

public interface ItemDao {
  //通过题目ID查询所有的选项
  public List<Item> queryItems(int titleId);
  
  //通过题目ID得到选项
  public Item getItemBytitleId(int titleId);
  
  public int deleteItemBytitleId(int titleId);
  
  public int addItem(Item item);
  
  //增加选项表
  public int addItem(int titleId,String[] itemNames,int[] scopes) throws Exception;
  
}
