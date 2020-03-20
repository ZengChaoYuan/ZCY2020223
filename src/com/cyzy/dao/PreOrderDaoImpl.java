package com.cyzy.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Map;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.MapHandler;
import org.apache.commons.dbutils.handlers.MapListHandler;

import com.cyzy.bean.PreOrder;
import com.cyzy.util.DBUtil;
import com.cyzy.util.JDBCUtil;

public class PreOrderDaoImpl implements PreOrderDao {
	private static QueryRunner runner=new QueryRunner(JDBCUtil.getDataSource());
	
	@Override
	public int addPreOrder(PreOrder preOrder) throws Exception {
		int result=0;
		String sql="INSERT INTO T_PREORDER(PREORDER_ID,USER_ID,PROBLEM_DESC,DIAGNOSE_REPLY,EVALUATE_CONTENT,PREORDER_PRICE,AREA_ID,CUSTOMER_ID,ORDER_STATUS,PREORDER_TIME,REPLY_TIME) "
				+ " VALUES(SEQ_T_PREORDER.NEXTVAL,?,?,'无','无',?,?,?,2,TO_DATE(?,'YYYY-MM-DD HH24'),TO_DATE('2020-03-12 14', 'yyyy-mm-dd hh24'))";
		Object[] params= {preOrder.getUserId(),preOrder.getProblemDesc(),preOrder.getPreorderPrice(),preOrder.getAreaId(),preOrder.getCustomerId(),preOrder.getPreorderTime()};
		result=runner.update(sql,params);
		return result;
	}
	
	@Override
	public int stopPreOrder(PreOrder preOrder) {
		int result=0;
		String sql="UPDATE T_PREORDER SET ORDER_STATUS=? WHERE PREORDER_Id=?";
		Object [] params= {preOrder.getOrderStatus(),preOrder.getPreorderId()};
		try {
			result=runner.update(sql, params);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	//客户评价咨询师
	@Override
	public int assessConsulter(PreOrder preOrder) {
		int result=0;
		String sql="UPDATE T_PREORDER SET ORDER_STATUS=5, EVALUATE_CONTENT=?, ASSESS_TIME=SYSDATE WHERE PREORDER_ID=?";
		Object [] params= {preOrder.getEvaluateContent(),preOrder.getPreorderId()};
		try {
			result=runner.update(sql, params);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	//咨询师诊断答复
	@Override
	public int assessReply(PreOrder preOrder) {
		int result=0;
		String sql="UPDATE T_PREORDER SET ORDER_STATUS=4, DIAGNOSE_REPLY=?, REPLY_TIME=SYSDATE WHERE PREORDER_ID=?";
		Object [] params= {preOrder.getDiagnoseReply(),preOrder.getPreorderId()};
		try {
			result=runner.update(sql, params);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	
	@Override
	public List<Map<String, Object>> queryAllPreOrderList() {
		String sql="SELECT A.PREORDER_ID,A.PROBLEM_DESC,B.AREA_NAME,C.CUSTOMER_ID,C.CUSTOMER_NAME,A.ORDER_STATUS, F.NAME,E.USER_ID,E.USER_NAME FROM T_PREORDER A\r\n " + 
				" LEFT JOIN T_AREA B ON A.AREA_ID=B.AREA_ID\r\n" + 
				" LEFT JOIN T_CUSTOMER C ON A.CUSTOMER_ID=C.CUSTOMER_ID\r\n" +  
				" LEFT JOIN T_USER E ON E.USER_ID=A.USER_ID \r\n" + 
				" LEFT JOIN T_DICT F ON F.VALUE=A.ORDER_STATUS WHERE F.CODE='order_status'";
		 List<Map<String, Object>> queryAllPreOrderList=null;
		 try {
			queryAllPreOrderList=runner.query(sql, new MapListHandler());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return queryAllPreOrderList;
	}

	
	@Override
	public List<Map<String, Object>> queryCustomerPreOrderList(int userId) {
	    String sql=" SELECT A.PREORDER_ID,A.PROBLEM_DESC,B.AREA_NAME,C.CUSTOMER_ID,C.CUSTOMER_NAME,A.ORDER_STATUS, F.NAME,E.USER_ID,E.USER_NAME FROM T_PREORDER A \r\n" + 
	    		" LEFT JOIN T_AREA B ON A.AREA_ID=B.AREA_ID\r\n " + 
	    		" LEFT JOIN T_CUSTOMER C ON A.CUSTOMER_ID=C.CUSTOMER_ID\r\n " + 
	    		" LEFT JOIN T_USER E ON E.USER_ID=A.USER_ID \r\n " + 
	    		" LEFT JOIN T_DICT F ON F.VALUE=A.ORDER_STATUS WHERE F.CODE='order_status' AND A.USER_ID=? ";
	    List<Map<String, Object>> queryAllPreOrderList=null;
	    Object[] params= {userId};
	    try {
			queryAllPreOrderList=runner.query(sql, params, new MapListHandler());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return queryAllPreOrderList;
	}

	
	@Override
	public List<Map<String, Object>> queryMyPreOrderList(int customerId)   {
		String sql=" SELECT A.PREORDER_TIME,A.ORDER_STATUS, A.USER_ID, C.USER_NAME,D.AREA_NAME,E.NAME FROM T_PREORDER A\r\n " + 
				" LEFT JOIN T_USER C ON C.USER_ID=A.USER_ID\r\n " + 
				" LEFT JOIN T_AREA D ON D.AREA_ID=A.AREA_ID\r\n " + 
				" LEFT JOIN T_DICT E ON E.VALUE=A.ORDER_STATUS WHERE E.CODE='order_status' AND A.CUSTOMER_ID=? ";
		List<Map<String, Object>> preOrders=null;
		Object[] params= {customerId};
		try {
			preOrders=runner.query(sql,params, new MapListHandler());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return preOrders;
	}
	
	@Override
	public Map<String, Object> queryMyPreOrderDetail(int preorderId) {
		String sql=" SELECT A.PREORDER_ID,A.ORDER_STATUS,B.CUSTOMER_NAME,C.AREA_NAME,A.PREORDER_TIME,E.NAME,A.PREORDER_PRICE,D.USER_NAME,A.PROBLEM_DESC,A.DIAGNOSE_REPLY,A.REPLY_TIME,A.EVALUATE_CONTENT FROM T_PREORDER A\r\n " + 
				" LEFT JOIN T_CUSTOMER B ON A.CUSTOMER_ID=B.CUSTOMER_ID\r\n" + 
				" LEFT JOIN T_AREA C ON A.AREA_ID=C.AREA_ID\r\n" + 
				" LEFT JOIN T_USER D ON A.USER_ID=D.USER_ID\r\n" + 
				" LEFT JOIN T_DICT E ON E.VALUE=A.ORDER_STATUS\r\n" + 
				" WHERE E.CODE='order_status' AND A.PREORDER_ID= ? ";
		Map<String,Object> orderDetail=null;
		Object[] params= {preorderId};
		 try {
			 orderDetail=runner.query(sql, params,new MapHandler());
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return orderDetail;
	}

	@Override
	public Map<String, Object> queryPreOrderDetailByIdStatus(int customerId, int orderStatus) {
		String sql="SELECT  A.PREORDER_TIME,A.USER_ID,B.CUSTOMER_NAME,C.AREA_NAME,F.NAME,A.PREORDER_PRICE,E.USER_NAME,A.PROBLEM_DESC,A.DIAGNOSE_REPLY,A.EVALUATE_CONTENT FROM T_PREORDER A \r\n" + 
				" LEFT JOIN T_CUSTOMER B ON A.CUSTOMER_ID=B.CUSTOMER_ID\r\n " + 
				" LEFT JOIN T_AREA C ON A.AREA_ID=C.AREA_ID\r\n " + 
				" LEFT JOIN T_USER E ON E.USER_ID=A.USER_ID\r\n " + 
				" LEFT JOIN T_DICT F ON F.VALUE=A.ORDER_STATUS WHERE F.CODE='order_status' AND B.CUSTOMER_ID=? AND A.ORDER_STATUS=?";
	    Map<String,Object> orderDetail=null;
	    Object[] params= {customerId,orderStatus};
	    try {
			orderDetail=runner.query(sql, params,new MapHandler());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return orderDetail;
	}

	@Override
	public List<Map<String, Object>> queryAreaListByUserId(int userId) {
		String sql=" SELECT A.AREA_NAME FROM T_AREA A\r\n " + 
				" LEFT JOIN T_USER_AREA B ON A.AREA_ID=B.AREA_ID\r\n " + 
				" LEFT JOIN T_USER C ON B.USER_ID=C.USER_ID WHERE C.USER_ID=? ";
		List<Map<String,Object>> areaList=null;
		Object[] params= {userId};
		try {
			areaList=runner.query(sql, params,new MapListHandler());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return areaList;
	}

	@Override
	public Map<String, Object> queryUserInfo(int userId) {
		String sql="SELECT USER_NAME,SCHOOL,PROFESSOR,INTRO,PROFESS_BACK FROM T_USER WHERE USER_ID=?";
		Map<String,Object> userInfo=null;
		Object[] params= {userId};
		try {
			userInfo=runner.query(sql, params,new MapHandler());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return userInfo;
	}

	@Override
	public List<Map<String, Object>> queryAllConsulterByAreaId(int areaId) {
		String sql=" SELECT A.USER_ID,A.USER_NAME FROM T_USER A\r\n " + 
				" LEFT JOIN T_USER_AREA B ON A.USER_ID=B.USER_ID\r\n " + 
				" LEFT JOIN T_AREA C ON B.AREA_ID=C.AREA_ID WHERE C.AREA_ID=? ";
		List<Map<String,Object>> areaDownUser=null;
		Object[] params= {areaId};
		try {
			areaDownUser=runner.query(sql, params,new MapListHandler());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return areaDownUser;
	}


	@Override
	public PreOrder queryPreOrderById(int preorderId) {
		Connection conn = DBUtil.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		PreOrder preOrder=null;
		String sql="SELECT PREORDER_ID,USER_ID,PROBLEM_DESC,DIAGNOSE_REPLY,EVALUATE_CONTENT,PREORDER_PRICE,AREA_ID,CUSTOMER_ID,ORDER_STATUS,PREORDER_TIME,REPLY_TIME FROM T_PREORDER WHERE PREORDER_ID=?";
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, preorderId);
			rs = ps.executeQuery();
			while(rs.next()) {
				preOrder=new PreOrder();
				preOrder.setPreorderId(rs.getInt("PREORDER_ID"));
				preOrder.setUserId(rs.getInt("USER_ID"));
				preOrder.setProblemDesc(rs.getString("PROBLEM_DESC"));
				preOrder.setDiagnoseReply(rs.getString("DIAGNOSE_REPLY"));
				preOrder.setEvaluateContent(rs.getString("EVALUATE_CONTENT"));
				preOrder.setPreorderPrice(rs.getInt("PREORDER_PRICE"));
				preOrder.setAreaId(rs.getInt("AREA_ID"));
				preOrder.setCustomerId(rs.getInt("CUSTOMER_ID"));
				preOrder.setOrderStatus(rs.getInt("ORDER_STATUS"));
				preOrder.setPreorderTime(rs.getString("PREORDER_TIME"));
				preOrder.setReplyTime(rs.getString("REPLY_TIME"));
			}
		} catch (SQLException e) {	
			e.printStackTrace();
		}finally {
			DBUtil.closeConn(conn, ps, rs);
		}
		return preOrder;
	}

	@Override
	public int queryMyPreOrderCount(PreOrder preOrder) {
		PreparedStatement ps = null;
		ResultSet rs = null;
		Connection conn = null;
		String sql="SELECT COUNT(0) FROM T_PREORDER WHERE CUSTOMER_ID= ?";
		int count=0;
		try {
			conn=DBUtil.getConnection();
			ps=conn.prepareStatement(sql);
			ps.setInt(1, preOrder.getCustomerId());
			rs=ps.executeQuery();
			while(rs.next()) {
				count=rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			DBUtil.closeConn(conn, ps, rs);
		}
		
		return count;
	}

	@Override
	public List<Map<String, Object>> queryMyPreOrders(PreOrder preOrder, int startIndex, int endIndex) {
		String sql="SELECT * FROM (SELECT  A.ORDER_STATUS, A.PREORDER_ID,A.CUSTOMER_ID,A.PREORDER_TIME, C.USER_NAME,D.AREA_NAME,E.NAME,ROWNUM AS RN FROM T_PREORDER A\r\n " + 
				"         LEFT JOIN T_USER C ON C.USER_ID=A.USER_ID\r\n " + 
				"         LEFT JOIN T_AREA D ON D.AREA_ID=A.AREA_ID\r\n " + 
				"         LEFT JOIN T_DICT E ON E.VALUE=A.ORDER_STATUS WHERE E.CODE='order_status' AND CUSTOMER_ID=? AND ROWNUM<='"+endIndex+"' ORDER BY A.PREORDER_TIME DESC) C WHERE C.RN>="+startIndex;
		List<Map<String, Object>> myPreOrders=null;
		Object [] params= {preOrder.getCustomerId()};
		try {
			myPreOrders=runner.query(sql,params, new MapListHandler());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return myPreOrders;
	}

	@Override
	public List<Map<String, Object>> queryAssessInfo(int userId) {
		String sql=" SELECT B.CUSTOMER_NAME,A.ASSESS_TIME,A.EVALUATE_CONTENT, A.ORDER_STATUS  FROM T_PREORDER A \r\n" + 
				" LEFT JOIN T_CUSTOMER B ON A.CUSTOMER_ID=B.CUSTOMER_ID WHERE USER_ID=? ";
		List<Map<String,Object>> assessInfo=null;
		Object[] params= {userId};
		try {
			assessInfo=runner.query(sql,params, new MapListHandler());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return assessInfo;
	}
	
	@Override
	public int queryAllPreOrderCount() {
		PreparedStatement ps = null;
		ResultSet rs = null;
		Connection conn = null;
		String sql="SELECT COUNT(0) FROM T_PREORDER";
		int count=0;
		try {
			conn = DBUtil.getConnection();
			ps=conn.prepareStatement(sql);
			rs=ps.executeQuery();
			while(rs.next()) {
				count=rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			DBUtil.closeConn(conn, ps, rs);
		}
		
		return count;
	}


	@Override
	public int queryMyCustomerOrderCount(PreOrder preOrder) {
		PreparedStatement ps = null;
		ResultSet rs = null;
		Connection conn = null;
		String sql="SELECT COUNT(0) FROM T_PREORDER WHERE USER_ID=?";
		int count=0;
		try {
			conn = DBUtil.getConnection();
			ps=conn.prepareStatement(sql);
			ps.setInt(1, preOrder.getUserId());
			rs=ps.executeQuery();
			while(rs.next()) {
				count=rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			DBUtil.closeConn(conn, ps, rs);
		}
		
		return count;
	}

	@Override
	public List<Map<String, Object>> queryMyCustomerOrders(PreOrder preOrder, int startIndex, int endIndex) {
		String sql="SELECT * FROM (SELECT A.PREORDER_ID,A.USER_ID,A.PREORDER_TIME,B.CUSTOMER_NAME,C.AREA_NAME,A.PROBLEM_DESC,A.ORDER_STATUS,D.NAME,ROWNUM AS RN FROM T_PREORDER A \r\n " + 
				" LEFT JOIN T_CUSTOMER B ON A.CUSTOMER_ID=B.CUSTOMER_ID  LEFT JOIN T_AREA C ON A.AREA_ID=C.AREA_ID \r\n " + 
				" LEFT JOIN T_DICT D ON A.ORDER_STATUS=D.VALUE WHERE D.CODE='order_status' AND A.USER_ID=? AND ROWNUM<='"+endIndex+"' ORDER BY A.PREORDER_TIME DESC \r\n" + 
				"  ) E WHERE E.RN>= "+startIndex;
		List<Map<String, Object>> customerOrders=null;
		Object [] params= {preOrder.getUserId()};
		try {
			customerOrders=runner.query(sql,params, new MapListHandler());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return customerOrders;
	}

	@Override
	public List<Map<String, Object>> queryAllPreOrders(int startIndex, int endIndex) {
        String sql="SELECT * FROM (SELECT A.PREORDER_ID,A.PREORDER_TIME,E.USER_ID,E.USER_NAME,B.AREA_NAME, C.CUSTOMER_ID,C.CUSTOMER_NAME,A.ASSESS_TIME,A.ORDER_STATUS, F.NAME,ROWNUM AS RN FROM T_PREORDER A \r\n " + 
        		"       LEFT JOIN T_AREA B ON A.AREA_ID=B.AREA_ID\r\n " + 
        		"       LEFT JOIN T_CUSTOMER C ON A.CUSTOMER_ID=C.CUSTOMER_ID \r\n" + 
        		"       LEFT JOIN T_USER E ON E.USER_ID=A.USER_ID  \r\n" + 
        		"       LEFT JOIN T_DICT F ON F.VALUE=A.ORDER_STATUS WHERE F.CODE='order_status' AND ROWNUM<='"+endIndex+"' ORDER BY A.PREORDER_TIME ASC) G WHERE G.RN>= "+startIndex;
        List<Map<String, Object>> allPreOrders=null;
		try {
			allPreOrders=runner.query(sql, new MapListHandler());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return allPreOrders;
	}


//	@Override
//	public List<Map<String, Object>> queryMyCustomerOrders(PreOrder preOrder, String startTime, String endTime,
//			int startIndex, int endIndex) {
//		String sql="SELECT * FROM (SELECT A.PREORDER_ID,A.USER_ID,A.PREORDER_TIME,B.CUSTOMER_NAME,C.AREA_NAME,A.PROBLEM_DESC,A.ORDER_STATUS,D.NAME,ROWNUM AS RN FROM T_PREORDER A \r\n" + 
//				" LEFT JOIN T_CUSTOMER B ON A.CUSTOMER_ID=B.CUSTOMER_ID  LEFT JOIN T_AREA C ON A.AREA_ID=C.AREA_ID\r\n" + 
//				" LEFT JOIN T_DICT D ON A.ORDER_STATUS=D.VALUE WHERE D.CODE='order_status' AND A.USER_ID=?  AND ROWNUM<='"+endTime+"' ORDER BY A.PREORDER_TIME DESC \r\n" + 
//				"  ) E WHERE E.RN>='"+startTime+"'";
//		
//		Object [] params= {preOrder.getUserId()};
//		List<Map<String, Object>> myCustomerOrders=null;
//		try {
//			myCustomerOrders=runner.query(sql, params,new MapListHandler());
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//		return myCustomerOrders;
//	}

	


	

}
