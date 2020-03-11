package com.cyzy.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;

import com.cyzy.bean.Item;
import com.cyzy.util.DBUtil;
import com.cyzy.util.JDBCUtil;

public class ItemDaoImpl implements ItemDao {
	private static QueryRunner runner=new QueryRunner(JDBCUtil.getDataSource());
	@Override
	public List<Item> queryItems(int titleId) {
		PreparedStatement ps=null;
		ResultSet rs = null;
		Connection conn = null;
		List<Item> itemList=new ArrayList<Item>();
		String sql="SELECT ITEM_ID,TITLE_ID,ITEM_NAME,SCOPE FROM T_ITEM WHERE TITLE_ID=?";
		try {
			conn = DBUtil.getConnection();
			ps= conn.prepareStatement(sql);
			ps.setInt(1, titleId);
			rs = ps.executeQuery();
			while(rs.next()) {
				int itemId=rs.getInt("ITEM_ID");
				int titleid=rs.getInt("TITLE_ID");
				String itemName=rs.getString("ITEM_NAME");
				int scope=rs.getInt("SCOPE");
				Item temp=new Item(itemId,titleid,itemName,scope);
				itemList.add(temp);
			}
		} catch (SQLException e) {	
			e.printStackTrace();
		}finally {
			DBUtil.closeConn(conn, ps, rs);
		}	
		return itemList;
	}

	@Override
	public Item getItemBytitleId(int titleId) {
		Connection conn = DBUtil.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		Item item=null;
		String sql="SELECT ITEM_ID,ITEM_NAME,SCOPE,TITLE_ID FROM T_ITEM WHERE TITLE_ID=?";
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, titleId);
			rs = ps.executeQuery();
			while(rs.next()) {
				item=new Item();
				item.setItemId(rs.getInt("ITEM_ID"));
				item.setItemName(rs.getString("ITEM_NAME"));
				item.setScope(rs.getInt("SCOPE"));
				item.setTitleId(rs.getInt("TITLE_ID"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			DBUtil.closeConn(conn, ps, rs);
		}
		return item;
	}

	@Override
	public int deleteItemBytitleId(int titleId) {
		Connection conn = DBUtil.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "DELETE FROM T_ITEM WHERE TITLE_ID = ?";
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, titleId);
			return ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			DBUtil.closeConn(conn, ps, rs);
		}
		return 0;
	}

	@Override
	public int addItem(Item item) {
		Connection conn=DBUtil.getConnection();
		PreparedStatement ps=null;
		ResultSet rs=null;
		String sql="INSERT INTO T_ITEM(ITEM_ID,TITLE_ID,ITEM_NAME,SCOPE) VALUES(SEQ_T_TITLE.NEXTVAL,?,?,?)";
		try {
			ps=conn.prepareStatement(sql);
			ps.setInt(1, item.getTitleId());
			ps.setString(2, item.getItemName());
			ps.setInt(3, item.getScope());
			return ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			DBUtil.closeConn(conn, ps, rs);
		}
		return 0;
	}

	@Override
	public int addItem(int titleId, String[] itemNames, int[] scopes) throws Exception {
		String sql="INSERT INTO T_ITEM(ITEM_ID,TITLE_ID,ITEM_NAME,SCOPE) VALUES(SEQ_T_TITLE.NEXTVAL,?,?,?)";
		Object[][]params=new Object[itemNames.length][scopes.length];
		for(int i=0;i<itemNames.length;i++) {
			params[i]=new Object[3];
			params[i][0]=titleId;
			params[i][1]=itemNames[i];
			params[i][2]=Integer.valueOf(scopes[i]);
		}
		return runner.batch(JDBCUtil.getConnection(), sql, params).length;
	}

    
}
