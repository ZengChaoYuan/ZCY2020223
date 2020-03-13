package com.cyzy.service;

import java.util.List;

import com.cyzy.bean.Area;
import com.cyzy.dao.AreaDao;
import com.cyzy.util.DaoFactory;

public class AreaServiceImpl implements AreaService {

	@Override
	public List<Area> queryArea() {
	   AreaDao areaDao=(AreaDao)DaoFactory.getDaoImpl(AreaDao.class.getName());
		return areaDao.queryArea();
	}

}
