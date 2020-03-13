package com.cyzy.service;

import java.util.List;
import java.util.Map;

import com.cyzy.bean.AssReport;
import com.cyzy.dao.AssReportDao;
import com.cyzy.util.DaoFactory;

public class AssReportServiceImpl implements AssReportService {

	@Override
	public List<AssReport> querymyReport(int customerId) {
		AssReportDao assReportDao=(AssReportDao)DaoFactory.getDaoImpl(AssReportDao.class.getName());
		return assReportDao.querymyReport(customerId);
	}

	@Override
	public List<Map<String, Object>> queryCustomerReport(AssReport assReport) {
		AssReportDao assReportDao=(AssReportDao)DaoFactory.getDaoImpl(AssReportDao.class.getName());
		return assReportDao.queryCustomerReport(assReport);
	}

	@Override
	public Map<String, Object> queryCustomerAss(int assReportId) {
		AssReportDao assReportDao=(AssReportDao)DaoFactory.getDaoImpl(AssReportDao.class.getName());
		return assReportDao.queryCustomerAss(assReportId);
	}

}
