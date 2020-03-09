package com.cyzy.service;

import com.cyzy.bean.Param;
import com.cyzy.dao.ParamDao;
import com.cyzy.util.DaoFactory;

public class ParamServiceImpl implements ParamService {

	@Override
	public Param getParamById(int paramId) {
		ParamDao paramDao=(ParamDao)DaoFactory.getDaoImpl(ParamDao.class.getName());
		return paramDao.getParamById(paramId);
	}

}
