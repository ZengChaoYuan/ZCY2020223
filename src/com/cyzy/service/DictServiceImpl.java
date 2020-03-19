package com.cyzy.service;

import java.util.List;
import java.util.Map;

import com.cyzy.dao.DictDao;
import com.cyzy.util.DaoFactory;

public class DictServiceImpl implements DictService {

	@Override
	public List<Map<String, Object>> queryOrderStatusList() {
		DictDao dictDao=(DictDao)DaoFactory.getDaoImpl(DictDao.class.getName());
		return dictDao.queryOrderStatusList();
	}

}
