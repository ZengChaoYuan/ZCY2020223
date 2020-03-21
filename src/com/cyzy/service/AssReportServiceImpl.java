package com.cyzy.service;

import java.util.List;
import java.util.Map;

import com.cyzy.bean.AssReport;
import com.cyzy.dao.AssReportDao;
import com.cyzy.util.DaoFactory;
import com.cyzy.util.Page;

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

	@Override
	public Page<Map<String, Object>> querymyReports(AssReport assReport, int currentPageNum) {
		AssReportDao assReportDao=(AssReportDao)DaoFactory.getDaoImpl(AssReportDao.class.getName());
		int totalRecordsNum=assReportDao.MyReportCounts(assReport);
		Page<Map<String,Object>> page=new Page<Map<String,Object>>(currentPageNum,totalRecordsNum,5);
		List<Map<String,Object>> userFunds=assReportDao.queryMyReports(assReport, page.getStartIndex(),page.getEndIndex());
		page.setRecords(userFunds);
		return page;
	}

	@Override
	public Page<Map<String, Object>> queryAllReports(int currentPageNum) {
		AssReportDao assReportDao=(AssReportDao)DaoFactory.getDaoImpl(AssReportDao.class.getName());
		int totalRecordsNum=assReportDao.AllReportCounts();
		Page<Map<String,Object>> page=new Page<Map<String,Object>>(currentPageNum,totalRecordsNum,5);
		List<Map<String,Object>> allReports=assReportDao.queryAllReports(page.getStartIndex(),page.getEndIndex());
		page.setRecords(allReports);
		return page;
	}

	@Override
	public int addAssReport(AssReport assReport) throws Exception {
		AssReportDao assReportDao=(AssReportDao)DaoFactory.getDaoImpl(AssReportDao.class.getName());
		return assReportDao.addAssReport(assReport);
	}

}
