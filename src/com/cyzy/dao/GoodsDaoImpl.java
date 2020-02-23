package com.cyzy.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.cyzy.bean.Goods;
import com.cyzy.util.DBUtil;

public class GoodsDaoImpl implements GoodsDao {

	@Override
	public int addGoods(Goods goods) {
		Connection conn=DBUtil.getConnection();
		PreparedStatement ps=null;
		ResultSet rs=null;
		String sql="INSERT INTO T_GOODS(GOODS_ID,GOODS_NAME) VALUES(SEQ_T_GOODS.NEXTVAL,?)";
		try {
			ps=conn.prepareStatement(sql);
			ps.setString(1, goods.getGoodsName());
			return ps.executeUpdate();
		} catch (SQLException e) {

			e.printStackTrace();
		}finally {
			DBUtil.closeConn(conn, ps, rs);
		}
		
		return 0;
	}

	@Override
	public int deleteGoods(int goodsId) {
		Connection conn=DBUtil.getConnection();
		PreparedStatement ps=null;
		ResultSet rs=null;
		String sql="DELETE FROM T_Goods WHERE Goods_ID=?";
		try {
			ps=conn.prepareStatement(sql);
			ps.setInt(1, goodsId);	
			return ps.executeUpdate();
		} catch (SQLException e) {
			
			e.printStackTrace();
		}finally {
			DBUtil.closeConn(conn, ps, rs);
		}
		return 0;
	}

	@Override
	public int updateGoods(Goods goods) {
		Connection conn=DBUtil.getConnection();
		PreparedStatement ps=null;
		ResultSet rs=null;

		 String sql = "UPDATE T_USER SET GOODS_NAME=? WHERE GOODS_ID=?";
		try {
			ps=conn.prepareStatement(sql);
			ps.setString(1, goods.getGoodsName());
			ps.setInt(2, goods.getGoodsId());
			return ps.executeUpdate();
		} catch (SQLException e) {
			
			e.printStackTrace();
		}finally {
			DBUtil.closeConn(conn, ps, rs);
		}
		
		return 0;
	}

	@Override
	public List<Goods> queryGoods(Goods goods) {
		List<Goods> goodsList=new ArrayList<Goods>();
		Connection conn=DBUtil.getConnection();
		PreparedStatement ps=null;
		ResultSet rs=null;
		String sql="SELECT GOODS_ID,GOODS_NAME FROM T_GOODS WHERE 1=1";
		try {
			
			ps=conn.prepareStatement(sql);
			
			rs=ps.executeQuery();
			while(rs.next()) {
				
				int goodsId=rs.getInt("GOODS_ID");
				String goodsName=rs.getString("GOODS_NAME");
				Goods temp=new Goods();
				temp.setGoodsId(goodsId);
				temp.setGoodsName(goodsName);
				goodsList.add(temp);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			DBUtil.closeConn(conn, ps, rs);
		}
	   return goodsList;
	}

}
