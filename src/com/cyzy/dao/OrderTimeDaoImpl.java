package com.cyzy.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.dbutils.QueryRunner;
import com.cyzy.bean.OrderTime;
import com.cyzy.util.DBUtil;
import com.cyzy.util.JDBCUtil;

public class OrderTimeDaoImpl implements OrderTimeDao {
	private static QueryRunner runner=new QueryRunner(JDBCUtil.getDataSource());
	@Override
	public int saveOrderTime(int userId, String orderDate, String[] orderHour) throws Exception {
		String sql="INSERT INTO T_ORDERTIME(ORDERTIME_ID,USER_ID,ORDER_DATE,ORDER_HOUR)VALUES "
				+ " (SEQ_T_ORDERTIME.NEXTVAL,?,TO_DATE(?, 'YYYY-MM-DD'),?)";
		Object[][]params=new Object[orderHour.length][];
		for(int i=0;i<orderHour.length;i++) {
			params[i]=new Object[3];
			params[i][0]=userId;
			params[i][1]=orderDate;
			params[i][2]=orderHour[i];
		}
		return runner.batch(JDBCUtil.getConnection(), sql, params).length;
	}
	
	@Override
	public int deleteTodayOrderTime(String orderDate) {
		int result = 0;
		String sql="DELETE FROM T_ORDERTIME WHERE ORDER_DATE= TO_DATE(?,'yyyy-mm-dd')";
		Object[] params = {orderDate};
		
		try {
			result = runner.update(sql, params);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	@Override
	public List<OrderTime> queryOrderTime(OrderTime orderTime) {
		return null;
	}
	
	@Override
	public List<OrderTime> queryOrderTime(int userId) {
		PreparedStatement ps=null;
		ResultSet rs = null;
		Connection conn = null;
		List<OrderTime> orderTimeList=new ArrayList<OrderTime>();
		String sql="SELECT ORDERTIME_ID,USER_ID,ORDER_DATE,ORDER_HOUR FROM T_ORDERTIME WHERE USER_ID=?";
		
		try {
			conn = DBUtil.getConnection();
			ps= conn.prepareStatement(sql);	
			ps.setInt(1, userId);
			rs = ps.executeQuery();
			while(rs.next()) {
				int ordertimeId=rs.getInt("ORDERTIME_ID");
				int userid=rs.getInt("USER_ID");
				String orderdate=rs.getString("ORDER_DATE");
				int orderHour=rs.getInt("ORDER_HOUR");
				OrderTime temp=new OrderTime(ordertimeId,userid,orderdate,orderHour);
				orderTimeList.add(temp);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			DBUtil.closeConn(conn, ps, rs);
		}
		return orderTimeList;
	}
	
	@Override
	public List<OrderTime> queryOrderTime(int userId, String orderDate) {
		PreparedStatement ps=null;
		ResultSet rs = null;
		Connection conn = null;
		List<OrderTime> orderTimeList=new ArrayList<OrderTime>();
		String sql="SELECT ORDERTIME_ID,USER_ID,ORDER_DATE,ORDER_HOUR FROM T_ORDERTIME WHERE USER_ID=?"
				+ " AND ORDER_DATE= TO_DATE(?,'yyyy-mm-dd')";
		try {
			conn = DBUtil.getConnection();
			ps= conn.prepareStatement(sql);
			ps.setInt(1, userId);
			ps.setString(2,orderDate);
			rs = ps.executeQuery();
			while(rs.next()) {
				int ordertimeId=rs.getInt("ORDERTIME_ID");
				int userid=rs.getInt("USER_ID");
				String orderdate=rs.getString("ORDER_DATE").substring(0,10);
				int orderHour=rs.getInt("ORDER_HOUR");
				OrderTime temp=new OrderTime(ordertimeId,userid,orderdate,orderHour);
				orderTimeList.add(temp);			
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			DBUtil.closeConn(conn, ps, rs);
		}
		return orderTimeList;
	}

	

}
