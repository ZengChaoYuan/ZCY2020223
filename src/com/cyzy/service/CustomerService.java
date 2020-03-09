package com.cyzy.service;

import java.util.List;

import com.cyzy.bean.Customer;
import com.cyzy.bean.CustomerStatis;
import com.cyzy.util.Page;

public interface CustomerService {
	  //��¼
	  public Customer login(String customerName,String password);
	  
	  //ע��
	  public int addCustomer(Customer customer) throws Exception;
	  
	  //��ҳ������
	  public Page<Customer> queryCustomerLike(Customer customer,int currentPageNum);
	  //��ҳ��������
	  public Page<Customer> queryCustomerList(Customer customer,int currentPageNum);
	  
	  //���ݿͻ�ID���õ��ͻ���������Ϣ
	  public Customer getCustomerById(int customerId);
	  
	  //�޸��û���״̬:����/����
	  public int updateUseStatus(Customer customer);
	  //�޸��û���״̬:�߼�ɾ��,ֻ�ı�״̬
	  public int updateDeleteStatus(Customer customer);
	  //��ʼ���ͻ�����
	  public int resetPassword(Customer customer);
	  //�Ñ�ͳ��
	  //��ʱ���ѯע���û�:����
	  public List<CustomerStatis> queryAllCustomerByWeek(CustomerStatis customerStatis);
	  //��ʱ���ѯע���û�:����
	  public List<CustomerStatis> queryAllCustomerByMonths(CustomerStatis customerStatis);
	  //��ʱ���ѯע���û�:������
	  public List<CustomerStatis> queryAllCustomerByYear(CustomerStatis customerStatis);
}
