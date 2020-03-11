package com.cyzy.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;

import com.cyzy.bean.Title;
import com.cyzy.util.DBUtil;
import com.cyzy.util.JDBCUtil;


public class TitleDaoImpl implements TitleDao {
	private static QueryRunner runner=new QueryRunner(JDBCUtil.getDataSource());
	@Override
	public List<Title> queryTitleBytitleId(int titleId) {
		PreparedStatement ps=null;
		ResultSet rs = null;
		Connection conn = null;
		List<Title> titleList=new ArrayList<Title>();
		String sql="SELECT TITLE_ID,AREA_ID,TITLE_NAME FROM T_TITLE WHERE TITLE_ID= ?";
		
		try {
			conn = DBUtil.getConnection();
			ps= conn.prepareStatement(sql);
			ps.setInt(1, titleId);
			rs = ps.executeQuery();
			while(rs.next()) {
				int titleid=rs.getInt("TITLE_ID");
				int areaid=rs.getInt("AREA_ID");
				String titleName=rs.getString("TITLE_NAME");
				Title temp=new Title(titleid,areaid,titleName);
				titleList.add(temp);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			DBUtil.closeConn(conn, ps, rs);
		}
		return titleList;
	}
	
	@Override
	public List<Title> queryTitles(int areaId) {
		PreparedStatement ps=null;
		ResultSet rs = null;
		Connection conn = null;
		List<Title> titleList=new ArrayList<Title>();
		String sql="SELECT TITLE_ID,AREA_ID,TITLE_NAME FROM T_TITLE WHERE AREA_ID=?";
		
		try {
			conn = DBUtil.getConnection();
			ps= conn.prepareStatement(sql);
			ps.setInt(1, areaId);
			rs = ps.executeQuery();
			while(rs.next()) {
				int titleId=rs.getInt("TITLE_ID");
				int areaid=rs.getInt("AREA_ID");
				String titleName=rs.getString("TITLE_NAME");
				Title temp=new Title(titleId,areaid,titleName);
				titleList.add(temp);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			DBUtil.closeConn(conn, ps, rs);
		}
		return titleList;
	}

	@Override
	public Title getTitleBytitleId(int titleId) {
		Connection conn = DBUtil.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		Title title=null;
		String sql="SELECT TITLE_ID,TITLE_NAME,AREA_ID FROM T_TITLE WHERE TITLE_ID=?";
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, titleId);
			rs = ps.executeQuery();
			while(rs.next()) {
				title=new Title();
				title.setTitleId(rs.getInt("TITLE_ID"));
				title.setTitleName(rs.getString("TITLE_NAME"));
				title.setAreaId(rs.getInt("AREA_ID"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			DBUtil.closeConn(conn, ps, rs);
		}
		return title;
	}

	@Override
	public Title getTitleByareaId(int areaId) {
		Connection conn = DBUtil.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		Title title=null;
		String sql="SELECT TITLE_ID,TITLE_NAME,AREA_ID FROM T_TITLE WHERE AREA_ID=?";
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, areaId);
			rs = ps.executeQuery();
			while(rs.next()) {
				title=new Title();
				title.setTitleId(rs.getInt("TITLE_ID"));
				title.setTitleName(rs.getString("TITLE_NAME"));
				title.setAreaId(rs.getInt("AREA_ID"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			DBUtil.closeConn(conn, ps, rs);
		}
		return title;
	}

	@Override
	public int deleteTitleBytitleId(int titleId) {
		Connection conn = DBUtil.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql="DELETE FROM T_TITLE WHERE TITLE_ID=?";
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
	public int addTitle(Title title) {
		Connection conn=DBUtil.getConnection();
		PreparedStatement ps=null;
		String sql="INSERT INTO T_TITLE(TITLE_ID,AREA_ID,TITLE_NAME) VALUES(?,?,?)";
		try {
			ps=conn.prepareStatement(sql);
			ps.setInt(1, title.getTitleId());
			ps.setInt(2, title.getAreaId());
			ps.setString(3, title.getTitleName());
			return ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			DBUtil.closeConn(conn, ps, null);
		}
		return 0;
	}

	@Override
	public int createTitleId() {
		Connection conn =DBUtil.getConnection();
		PreparedStatement ps = null;	
		ResultSet rs=null;
		String sql="SELECT SEQ_T_TITLE.NEXTVAL AS TITLE_ID FROM DUAL";
		int titleId=0;
		try {
			ps= conn.prepareStatement(sql);
			rs=ps.executeQuery();
			while(rs.next()) {
				titleId=rs.getInt("TITLE_ID");
			}
		} catch (SQLException e) {	
			e.printStackTrace();
		}finally {
			DBUtil.closeConn(conn, ps, rs);
		}
		
		
		return titleId;
	}

	@Override
	public int updateTitle(Title title) {
			int count=0;
			String sql="UPDATE T_TITLE SET AREA_ID=? , TITLE_NAME=? WHERE TITLE_ID=?";
			Object [] params= {title.getAreaId(),title.getTitleName(),title.getTitleId()};
				try {
					count=runner.update(sql,params);
				} catch (SQLException e) {
					e.printStackTrace();
				}
			return count;
	}

}
