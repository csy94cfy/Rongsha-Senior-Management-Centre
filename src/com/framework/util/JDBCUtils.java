	package com.framework.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;

public class JDBCUtils {
	private static Connection conn = null;
	private static PreparedStatement ps = null;
	private static ResultSet rs = null;
/***************************************************静态模块*****************************************************************/
	//加载驱动
	static {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
/***************************************************共有方法*****************************************************************/
	//执行非查询语句
	public static long executeSQL(Map<String, String> map,String sql) throws SQLException {
		initConn(map);
		long l = 0;
		if (StringUtil.isNull(sql)) {
			return l;
		}
		ps = conn.prepareStatement(sql);
		l = ps.executeUpdate();
		close(null, ps, conn);
		return l;
	}
	//分页数据条数
	public static long selectCount(Map<String, String> map,String sql) {
		initConn(map);
		long l = 0;
		if (StringUtil.isNull(sql)) {
			return l;
		}
		try {
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			if(rs.next()) {
				l = rs.getInt(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(rs, ps, conn);
		}
		return l;
	}
	//执行查询语句-结果集合
	public static List<Map<String, Object>> selectList(Map<String, String> map,String sql) {
		initConn(map);
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		if (StringUtil.isNull(sql)) {
			return null;
		}
		try {
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			ResultSetMetaData md = rs.getMetaData();
			while(rs.next()) {
				Map<String, Object> mudel = new HashMap<String, Object>();
				for(int i=1;i<=md.getColumnCount();i++) {
					if (rs.getObject(i) == null) {
						mudel.put(md.getColumnLabel(i), null);
					}else {
						mudel.put(md.getColumnLabel(i), checkDate(rs.getObject(i).toString()));
					}
				}
				list.add(mudel);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			close(rs, ps, conn);
		}
		return list;
	}
	//执行查询语句-结果集合(返回对象)
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static List selectList(Map<String, String> map,Class c,String sql) throws Exception {
		initConn(map);
		List list = new ArrayList();
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			ResultSetMetaData md = rs.getMetaData();
			while(rs.next()) {
				Object obj = c.newInstance();
				for(int i=1;i<=md.getColumnCount();i++) {
					if (rs.getObject(i) == null) {
						BeanUtils.setProperty(obj, md.getColumnLabel(i), null);
					}else {
						BeanUtils.setProperty(obj, md.getColumnLabel(i), checkDate(rs.getObject(i).toString()));
					}
				}
				list.add(obj);
			}
		close(rs, ps, conn);
		return list;
	}

	//执行系统语句查询该表的创表语句
	public static String selectCreate(Map<String, String> map,String tableName) {
		initConn(map);
		String DDL = "";
		if (StringUtil.isNull(tableName)) {
			return null;
		}
		try {
			ps = conn.prepareStatement("show create table "+tableName);
			rs = ps.executeQuery();
			if (rs.next()) {
				DDL = rs.getObject(2).toString();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			close(rs, ps, conn);
		}
		return DDL;
	}
	//输入表名（或+条件）查询数据的insert语句
	public static String selectInsert(Map<String, String> map,String tableName,String paramter) throws SQLException {
		initConn(map);
		String columns = "";
		String value = "";
		if (StringUtil.isNull(tableName)) {
			return null;
		}
		if (StringUtil.isNull(paramter)) {
			ps = conn.prepareStatement("select * from "+tableName);
		} else {
			ps = conn.prepareStatement("select * from "+tableName+" "+paramter);
		}
		rs = ps.executeQuery();
		if (rs.next()) {
			do{
				ResultSetMetaData md = rs.getMetaData();
				String rest = "";
				String column = "";
				for (int i = 1; i <= md.getColumnCount(); i++) {
					column += "," + md.getColumnLabel(i);
					if (rs.getObject(i) == null) {
						rest += ",null";
					}else {
						rest += ",'" + checkDate(rs.getObject(i).toString())+"'";
					}
				}
				value += "," + "(" + rest.substring(1) + ")";
				columns = column;
			}while(rs.next());
		}else {
			return null;
		}
		close(rs, ps, conn);
		return "INSERT INTO "+tableName+" ("+columns.substring(1)+") VALUE "+value.substring(1);
	}
	//表结构对比
	@SuppressWarnings("rawtypes")
	public static boolean checkStructure(Map<String, String> mapSource,String tableNameSource,Map<String, String> mapTarget,String tableNameTarget) {
		List listSource = selectStructure(mapSource,tableNameSource);
		List listTarget = selectStructure(mapTarget,tableNameTarget);
		boolean flag = true;
		for (int i = 0; i < listSource.size(); i++) {
			if (!listSource.get(i).equals(listTarget.get(i))) {
				return false;
			}
		}
		return flag;
	}
/***************************************************私有方法*****************************************************************/
	//获取路径、账号与密码
	private static void initConn(Map<String, String> map){
		conn = getConnection(map.get("url"),map.get("userName"),map.get("password"));
	}
	//获取链接
	private static Connection getConnection(String url,String userName,String password) {
		try {
			conn = DriverManager.getConnection(url,userName,password);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return conn;
	}
	//关流
	private static void close(ResultSet rs,PreparedStatement ps,Connection conn) {
		try {
			if(rs!=null) {
				rs.close();
			}
			if(ps!=null) {
				ps.close();
			}
			if(conn!=null) {
				conn.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	//验证时间格式方法（去掉.0）
	@SuppressWarnings("unused")
	private static String checkDate(String dateString) {
		SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.0");
		boolean dateflag=true;
		try {
			Date date = format.parse(dateString);
		} catch (ParseException e) {
			dateflag=false;
		}
		if (dateflag) {
			return dateString.substring(0, 19);
		}else {
			return dateString;
		}
	}
	//查询表结构
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private static List selectStructure(Map<String, String> map,String tableName) {
		initConn(map);
		List list = new ArrayList();
		if (StringUtil.isNull(tableName)) {
			return null;
		}
		try {
			ps = conn.prepareStatement("desc "+tableName);
			rs = ps.executeQuery();
			ResultSetMetaData md = rs.getMetaData();
			while(rs.next()) {
				Map mudel = new HashMap();
				for(int i=1;i<=md.getColumnCount();i++) {
					if (rs.getObject(i) == null) {
						mudel.put(md.getColumnLabel(i), null);
					}else {
						mudel.put(md.getColumnLabel(i), checkDate(rs.getObject(i).toString()));
					}
				}
				list.add(mudel);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			close(rs, ps, conn);
		}
		return list;
	}
}
