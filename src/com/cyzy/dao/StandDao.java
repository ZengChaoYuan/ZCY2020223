package com.cyzy.dao;

import com.cyzy.bean.Stand;

public interface StandDao {
 
	//通过标准ID来得到整个标准
	public Stand getStandByStandId(int standId);
  
}
