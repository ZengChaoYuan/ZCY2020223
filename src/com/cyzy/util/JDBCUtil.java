package com.cyzy.util;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import com.mchange.v2.c3p0.ComboPooledDataSource;

public class JDBCUtil {
   private static DataSource dataSource=null;//��������Դ����
   
   private static ThreadLocal<Connection> tl=new ThreadLocal<Connection>();
   static {
	   if(dataSource == null) {
		   dataSource=new ComboPooledDataSource();//ר�Ŵ���C3P0����Դ:��Դ����Դ
		   //�൱�ڴ���һ�����Ӷ���ĳ��ӣ�����dataSource������Դ�кܶ�����,���Ƕ�Ҫʵ��DataSource��
	   }
   }
   
   public static DataSource getDataSource() {
	   return dataSource;
   }
   //��ȡ���Ӷ���
   public static Connection getConnection() throws SQLException {
	   Connection con=tl.get();
	   if(con==null) {
		   con=dataSource.getConnection();
		   tl.set(con);
	   }
	    
	return con;
   }
   //��������
   public static void openTransaction() throws SQLException {
	   getConnection().setAutoCommit(false);
   }
   //�ύ����
   public static void commitTransaction() throws SQLException {
	   getConnection().commit();
   }
   //�ع�����
   public static void rollbackTransaction() throws SQLException {
	   getConnection().rollback();
   }
   //�ر�����
   public static void closeConnection() throws SQLException {
	   getConnection().close();
	   tl.remove();
   }
   
}
