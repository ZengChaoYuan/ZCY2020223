package com.cyzy.dao;

import java.util.List;

import com.cyzy.bean.Customer;
import com.cyzy.bean.CustomerStatis;



public interface CustomerDao {//ǰ̨�û���
  //��¼
  public Customer login(String customerName,String password);
  
  //ע��
  public int addCustomer(Customer customer) throws Exception;
  
  //��ҳ��ѯ
  //1.��ѯ�ж��������������ļ�¼: �ܼ�¼��
  public int queryCount(Customer customer);
  //2.��ҳ��ģ����ѯ
  public List<Customer> queryCustomerLike(Customer customer,int startIndex,int endIndex);
  //��ҳ��������
  public List<Customer> queryCustomerList(Customer customer,int startIndex,int endIndex);
  
  
  //���ݿͻ�ID���õ��ͻ���������Ϣ
  public Customer getCustomerById(int customerId);
  
  //�޸��û����
  public int updateBalance(Customer customer);
  
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
