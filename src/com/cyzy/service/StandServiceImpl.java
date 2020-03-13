package com.cyzy.service;

import com.cyzy.bean.Stand;
import com.cyzy.dao.StandDao;
import com.cyzy.util.DaoFactory;

public class StandServiceImpl implements StandService {

	@Override
	public Stand getStandByStandId(int standId) {
		StandDao standDao=(StandDao)DaoFactory.getDaoImpl(StandDao.class.getName());
		return standDao.getStandByStandId(standId);
	}

}
