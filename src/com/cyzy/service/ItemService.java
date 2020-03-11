package com.cyzy.service;

import java.util.List;

import com.cyzy.bean.Item;

public interface ItemService {
	  //��ѯ���е�ѡ��
	  public List<Item> queryItems(int titleId);
	  
	  //ͨ����ĿID�õ�ѡ��
	  public Item getItemBytitleId(int titleId);
	  
	  public int deleteItemBytitleId(int titleId);
	  
	  public int addItem(Item item);
	  
	  //����:��������
	  public int addItem(int titleId,String[] itemNames,int[] scopes) throws Exception;
}
