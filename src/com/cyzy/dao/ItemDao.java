package com.cyzy.dao;

import java.util.List;

import com.cyzy.bean.Item;

public interface ItemDao {
  //ͨ����ĿID��ѯ���е�ѡ��
  public List<Item> queryItems(int titleId);
  
  //ͨ����ĿID�õ�ѡ��
  public Item getItemBytitleId(int titleId);
  
  public int deleteItemBytitleId(int titleId);
  
  public int addItem(Item item);
  
  //����ѡ���
  public int addItem(int titleId,String[] itemNames,int[] scopes) throws Exception;
  
}
