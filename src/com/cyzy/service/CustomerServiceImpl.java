package com.cyzy.service;

import java.util.List;

import com.cyzy.bean.Customer;
import com.cyzy.bean.CustomerStatis;
import com.cyzy.dao.CustomerDao;
import com.cyzy.util.DaoFactory;
import com.cyzy.util.Page;

public class CustomerServiceImpl implements CustomerService {

	@Override
	public Customer login(String customerName, String password) {
		CustomerDao customerDao = (CustomerDao) DaoFactory.getDaoImpl(CustomerDao.class.getName());
		return customerDao.login(customerName, password);
	}

	@Override
	public int addCustomer(Customer customer) throws Exception {
		CustomerDao customerDao = (CustomerDao) DaoFactory.getDaoImpl(CustomerDao.class.getName());
		return customerDao.addCustomer(customer);
	}

	@Override
	public Customer getCustomerById(int customerId) {
		CustomerDao customerDao = (CustomerDao) DaoFactory.getDaoImpl(CustomerDao.class.getName());
		return customerDao.getCustomerById(customerId);
	}

	@Override
	public Page<Customer> queryCustomerLike(Customer customer, int currentPageNum) {
		CustomerDao customerDao = (CustomerDao) DaoFactory.getDaoImpl(CustomerDao.class.getName());
		int totalRecordsNum = customerDao.queryCount(customer);
		// 获得分页对象,还差一个数据记录(业务数据)
		Page<Customer> page = new Page<Customer>(currentPageNum, totalRecordsNum, 5);
		// 查询符合条件指定数据
		List<Customer> customerList = customerDao.queryCustomerLike(customer, page.getStartIndex(), page.getEndIndex());
		page.setRecords(customerList);
		return page;
	}

	@Override
	public Page<Customer> queryCustomerList(Customer customer, int currentPageNum) {
		CustomerDao customerDao = (CustomerDao) DaoFactory.getDaoImpl(CustomerDao.class.getName());
		int totalRecordsNum = customerDao.queryCount(customer);
		// 获得分页对象,还差一个数据记录(业务数据)
		Page<Customer> page = new Page<Customer>(currentPageNum, totalRecordsNum, 5);
		// 查询符合条件指定数据
		List<Customer> customerList = customerDao.queryCustomerList(customer, page.getStartIndex(), page.getEndIndex());
		page.setRecords(customerList);
		return page;
	}

	@Override
	public List<CustomerStatis> queryAllCustomerByWeek(CustomerStatis customerStatis) {//查询客户本周
		CustomerDao customerDao = (CustomerDao) DaoFactory.getDaoImpl(CustomerDao.class.getName());
		return customerDao.queryAllCustomerByWeek(customerStatis);
	}

	@Override
	public List<CustomerStatis> queryAllCustomerByYear(CustomerStatis customerStatis) {//查询客户本年
		CustomerDao customerDao = (CustomerDao) DaoFactory.getDaoImpl(CustomerDao.class.getName());
		return customerDao.queryAllCustomerByYear(customerStatis);
	}
	@Override
	public List<CustomerStatis> queryAllCustomerByMonths(CustomerStatis customerStatis) {//查询客户本月
		CustomerDao customerDao = (CustomerDao) DaoFactory.getDaoImpl(CustomerDao.class.getName());
		return customerDao.queryAllCustomerByMonths(customerStatis);
	}

	@Override
	public int updateUseStatus(Customer customer) {
		CustomerDao customerDao = (CustomerDao) DaoFactory.getDaoImpl(CustomerDao.class.getName());
		return customerDao.updateUseStatus(customer);
	}

	@Override
	public int updateDeleteStatus(Customer customer) {
		CustomerDao customerDao = (CustomerDao) DaoFactory.getDaoImpl(CustomerDao.class.getName());
		return customerDao.updateDeleteStatus(customer);
	}

	@Override
	public int resetPassword(Customer customer) {
		CustomerDao customerDao = (CustomerDao) DaoFactory.getDaoImpl(CustomerDao.class.getName());
		return customerDao.resetPassword(customer);
	}

	

}
