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
		String sql="INSERT INTO T_GOODS(GOODS_ID,GOODS_NAME,GOODS_LCLASS,GOODS_SCLASS,STATUS) VALUES(SEQ_T_GOODS.NEXTVAL,?,?,?,?)";
		try {
			ps=conn.prepareStatement(sql);
			ps.setString(1, goods.getGoodsName());
			ps.setString(2, goods.getGoodsLClass());
			ps.setString(3, goods.getGoodsSClass());
			ps.setInt(4, goods.getStatus());
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
		String sql="DELETE FROM T_GOODS WHERE GOODS_ID = ? ";
		try {
			ps=conn.prepareStatement(sql);
			ps.setInt(1, goodsId);	
			return ps.executeUpdate();
		} catch (SQLException e) {
			
			e.printStackTrace();
		}finally {
			DBUtil.closeConn(conn, ps, null);
		}
		return 0;
	}

	@Override
	public int updateGoods(Goods goods) {
		Connection conn=DBUtil.getConnection();
		PreparedStatement ps=null;
		ResultSet rs=null;

		 String sql = "UPDATE T_GOODS SET GOODS_NAME=?,GOODS_LCLASS=?,GOODS_SCLASS=?,STATUS=? WHERE GOODS_ID=?";
		try {
			ps=conn.prepareStatement(sql);
			ps.setString(1, goods.getGoodsName());
			ps.setString(2,goods.getGoodsLClass());
			ps.setString(3,goods.getGoodsSClass());
			ps.setInt(4, goods.getStatus());
			ps.setInt(5, goods.getGoodsId());
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
		String sql="SELECT GOODS_ID,GOODS_NAME,GOODS_LClass,Goods_SClass,STATUS FROM T_GOODS WHERE 1=1";
		try {
			
			ps=conn.prepareStatement(sql);
			
			rs=ps.executeQuery();
			while(rs.next()) {
				
				int goodsId=rs.getInt("GOODS_ID");
				String goodsName=rs.getString("GOODS_NAME");
				String goodsLClass=rs.getString("GOODS_LClass");
				String goodsSClass=rs.getString("GOODS_SClass");
				int status=rs.getInt("STATUS");
				Goods temp=new Goods();
				temp.setGoodsId(goodsId);
				temp.setGoodsName(goodsName);
				temp.setGoodsLClass(goodsLClass);
				temp.setGoodsSClass(goodsSClass);
				temp.setStatus(status);
				goodsList.add(temp);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			DBUtil.closeConn(conn, ps, rs);
		}
	   return goodsList;
	}

	@Override
	public Goods getGoodsById(int goodsId) {
		Connection conn = DBUtil.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		Goods goods=null;
		String sql="SELECT GOODS_ID,GOODS_NAME,GOODS_LClass,Goods_SClass,STATUS FROM T_GOODS WHERE GOODS_ID=? ";
		try {
			ps=conn.prepareStatement(sql);
			ps.setInt(1, goodsId);
			rs=ps.executeQuery();
			while(rs.next()) {
				goods=new Goods();
				goods.setGoodsId(rs.getInt("GOODS_ID"));
				goods.setGoodsName(rs.getString("GOODS_NAME"));
				goods.setGoodsLClass(rs.getString("GOODS_LClass"));
				goods.setGoodsSClass(rs.getString("Goods_SClass"));
				goods.setStatus(rs.getInt("STATUS"));
			}
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}finally {
			DBUtil.closeConn(conn, ps, rs);
		}
		
		return goods;
	}

	

}
