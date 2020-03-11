package com.cyzy.service;

import java.util.List;

import com.cyzy.bean.Item;
import com.cyzy.dao.ItemDao;
import com.cyzy.util.DaoFactory;

public class ItemServiceImpl implements ItemService {

	@Override
	public List<Item> queryItems(int titleId) {
		ItemDao itemDao=(ItemDao)DaoFactory.getDaoImpl(ItemDao.class.getName());
		return itemDao.queryItems(titleId);
	}

	@Override
	public Item getItemBytitleId(int titleId) {
		ItemDao itemDao=(ItemDao)DaoFactory.getDaoImpl(ItemDao.class.getName());
		return itemDao.getItemBytitleId(titleId);
	}

	@Override
	public int deleteItemBytitleId(int titleId) {
		ItemDao itemDao=(ItemDao)DaoFactory.getDaoImpl(ItemDao.class.getName());
		return itemDao.deleteItemBytitleId(titleId);
	}

	@Override
	public int addItem(Item item) {
		ItemDao itemDao=(ItemDao)DaoFactory.getDaoImpl(ItemDao.class.getName());
		return itemDao.addItem(item);
	}

	@Override
	public int addItem(int titleId, String[] itemNames, int[] scopes) throws Exception {
		ItemDao itemDao=(ItemDao)DaoFactory.getDaoImpl(ItemDao.class.getName());
		return itemDao.addItem(titleId, itemNames, scopes);
	}

}
