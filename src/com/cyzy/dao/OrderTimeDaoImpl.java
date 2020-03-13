package com.cyzy.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.cyzy.bean.OrderTime;
import com.cyzy.util.DBUtil;

public class OrderTimeDaoImpl implements OrderTimeDao {

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
	public boolean saveOrderTime(int userId, String orderDate, String[] orderHour) {
		
		return false;
	}

	

	

}
