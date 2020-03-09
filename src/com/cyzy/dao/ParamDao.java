package com.cyzy.dao;

import com.cyzy.bean.Param;

public interface ParamDao {//参数表
	//根据参数ID来拿到参数的完整信息包括重置密码
	public Param getParamById(int paramId);
   
}
