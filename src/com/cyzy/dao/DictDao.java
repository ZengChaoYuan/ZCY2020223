package com.cyzy.dao;

import java.util.List;
import java.util.Map;

public interface DictDao {
    //查询出完成状态
	public List<Map<String,Object>> queryOrderStatusList();
}
