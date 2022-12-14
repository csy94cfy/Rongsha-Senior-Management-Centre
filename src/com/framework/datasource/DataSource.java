package com.framework.datasource;

import java.sql.SQLException;
import java.sql.Wrapper;

import javax.sql.CommonDataSource;

import com.mysql.jdbc.Connection;

public interface DataSource extends CommonDataSource, Wrapper {

	/**
	 * <p>
	 * Attempts to establish a connection with the data source that this
	 * <code>DataSource</code> object represents.
	 *
	 * @return a connection to the data source
	 * @exception SQLException
	 *连接数据库失败会抛SQLException
	 */
	Connection getConnection() throws SQLException;

	/**
	 * <p>
	 * Attempts to establish a connection with the data source that this
	 * <code>DataSource</code> object represents.
	 *
	 * @param username
	 *            the database user on whose behalf the connection is being made
	 * @param password
	 *            the user's password
	 * @return a connection to the data source
	 * @exception SQLException
	 *                if a database access error occurs
	 * @since 1.4
	 */
	Connection getConnection(String username, String password) throws SQLException;

}