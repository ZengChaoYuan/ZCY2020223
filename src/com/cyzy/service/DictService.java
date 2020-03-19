package com.cyzy.service;

import java.util.List;
import java.util.Map;

public interface DictService {
	//查询出完成状态
	public List<Map<String,Object>> queryOrderStatusList();
}
