package com.cyzy.service;

import com.cyzy.bean.Stand;

public interface StandService {
	//通过标准ID来得到整个标准
		public Stand getStandByStandId(int standId);
}
