package com.cyzy.dao;

import java.util.List;

import com.cyzy.bean.Log;
import com.cyzy.bean.LogInf;

public interface LogDao {
	// 按时间查询日志:本周
	public List<Log> queryAllLogByTime(Log log);

    //按时间查询日志:近半年
	public List<Log> queryAllLogByYear(Log log);

	// 按用户查询日志:开始时间和结束时间
	public List<LogInf> queryAllLogByUser(String startTime, String endTime);

}
