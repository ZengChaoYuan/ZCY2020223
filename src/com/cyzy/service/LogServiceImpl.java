package com.cyzy.service;

import java.util.List;

import com.cyzy.bean.Log;
import com.cyzy.bean.LogInf;
import com.cyzy.dao.LogDao;
import com.cyzy.util.DaoFactory;

public class LogServiceImpl implements LogService {

	@Override
	public List<Log> queryAllLogByTime(Log log) {
		LogDao logDao=(LogDao)DaoFactory.getDaoImpl(LogDao.class.getName());
		return logDao.queryAllLogByTime(log);
	}

	@Override
	public List<LogInf> queryAllLogByUser(String startTime, String endTime) {
		LogDao logDao=(LogDao)DaoFactory.getDaoImpl(LogDao.class.getName());
		return logDao.queryAllLogByUser(startTime, endTime);
	}

	@Override
	public List<Log> queryAllLogByYear(Log log) {
		LogDao logDao=(LogDao)DaoFactory.getDaoImpl(LogDao.class.getName());
		return logDao.queryAllLogByYear(log);
	}

}
