package com.cyzy.util;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import com.mchange.v2.c3p0.ComboPooledDataSource;

public class JDBCUtil {
   private static DataSource dataSource=null;//创建数据源对象
   
   private static ThreadLocal<Connection> tl=new ThreadLocal<Connection>();
   static {
	   if(dataSource == null) {
		   dataSource=new ComboPooledDataSource();//专门创建C3P0数据源:开源数据源
		   //相当于创建一个连接对象的池子，就是dataSource。数据源有很多类型,但是都要实现DataSource。
	   }
   }
   
   public static DataSource getDataSource() {
	   return dataSource;
   }
   //获取连接对象
   public static Connection getConnection() throws SQLException {
	   Connection con=tl.get();
	   if(con==null) {
		   con=dataSource.getConnection();
		   tl.set(con);
	   }
	    
	return con;
   }
   //开启事务
   public static void openTransaction() throws SQLException {
	   getConnection().setAutoCommit(false);
   }
   //提交事务
   public static void commitTransaction() throws SQLException {
	   getConnection().commit();
   }
   //回滚事务
   public static void rollbackTransaction() throws SQLException {
	   getConnection().rollback();
   }
   //关闭连接
   public static void closeConnection() throws SQLException {
	   getConnection().close();
	   tl.remove();
   }
   
}
