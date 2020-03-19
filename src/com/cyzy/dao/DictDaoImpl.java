package com.cyzy.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.MapListHandler;

import com.cyzy.util.JDBCUtil;

public class DictDaoImpl implements DictDao {
	private static QueryRunner runner=new QueryRunner(JDBCUtil.getDataSource());
	@Override
	public List<Map<String, Object>> queryOrderStatusList() {
		String sql="SELECT VALUE,NAME FROM T_DICT WHERE CODE='order_status'";
		List<Map<String, Object>>  queryOrderStatusList=null;
		try {
			queryOrderStatusList=runner.query(sql, new MapListHandler());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return queryOrderStatusList;
	}

}
