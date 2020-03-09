package com.cyzy.dao;

import com.cyzy.bean.Customer;

public class CustomerDaoText {
	public static void main(String[] args) {
		Customer customer=new Customer();
	  CustomerDao cusDao=new CustomerDaoImpl();
	  int result=0;
	  try {
		result=cusDao.addCustomer(customer);
	} catch (Exception e) {
		e.printStackTrace();
	}
	  System.out.println(result);
	}
}
