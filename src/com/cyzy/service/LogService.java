package com.cyzy.service;

import java.util.List;

import com.cyzy.bean.Log;
import com.cyzy.bean.LogInf;

public interface LogService {

	// ��ʱ���ѯ��־:����
	public List<Log> queryAllLogByTime(Log log);
	//��ʱ���ѯ��־:������
	public List<Log> queryAllLogByYear(Log log);
	
	// ���û���ѯ��־:��ʼʱ��ͽ���ʱ��
	public List<LogInf> queryAllLogByUser(String startTime, String endTime);
}
