package com.cyzy.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.cyzy.bean.Area;
import com.cyzy.util.DBUtil;

public class AreaDaoImpl implements AreaDao {

	@Override
	public List<Area> queryArea() {
		Connection conn=DBUtil.getConnection();
		PreparedStatement ps=null;
		ResultSet rs=null;
		List<Area> areaList=new ArrayList<Area>();
		String sql="SELECT AREA_ID,AREA_NAME FROM T_AREA ";
		try {
			ps=conn.prepareStatement(sql);
			rs=ps.executeQuery();
			while(rs.next()) {
				int areaid=rs.getInt("AREA_ID");
				String areaName=rs.getString("AREA_NAME");
			    Area temp=new Area(areaid,areaName);
			    areaList.add(temp);
			}
		} catch (SQLException e) {
	
			e.printStackTrace();
		}
		return areaList;
	}

}
