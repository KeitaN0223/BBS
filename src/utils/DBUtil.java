package utils;

import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.SQLException

import BBS.exception.SQLRuntimeException;

/**
 * DB(コネクション関係)のユーティリティー
 */
public class DBUtil{

	private static final String DRIVER = "com.mysql.jdbc.Driver";
	private static final String URL = "jdbc:mysql://localhost/BBS";
	private static final String USER = "root";
	private static final String PASSWORD = "adriano0223";

	static{

		try{
			Class.forName(DRIVER);
		}catch(ClassNotFoundException e) {
			throw new RuntimeException(e);
		}

		/**
		 * コネクションを取得
		 * @return
		 */
		public static Connection getConnection(){

			try{
				Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);

				connection.setAutoCommit(false);

				return connection;
			}catch (SQLException e){
				throw new SQLRuntimeException(e);
			}

		}
		/**
		 * コミット
		 * @param connection
		 */
		public static void commit(Connection connection){
	}

;}
