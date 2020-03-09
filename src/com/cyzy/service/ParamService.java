package com.cyzy.service;

import com.cyzy.bean.Param;

public interface ParamService {
	 //根据参数ID来拿到参数的完整信息包括重置密码
	public Param getParamById(int paramId);
}
