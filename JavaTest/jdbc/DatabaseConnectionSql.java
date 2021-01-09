package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
/**
 * 数据库连接
 * @author ASUS
 *
 */
public class DatabaseConnectionSql {
	private static final String DBRIVER="com.microsoft.sqlserver.jdbc.SQLServerDriver";
	private static final String DBURL="jdbc:sqlserver://localhost:1433;DatabaseName=Java";
	private static final String DBUSER="sa";
	private static final String PASSWORD="2019401333";
	private Connection conn=null;
	public DatabaseConnectionSql() {
		try {
			Class.forName(DBRIVER);
			this.conn=DriverManager.getConnection(DBURL,DBUSER,PASSWORD);
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	public Connection getConnection() {
		return this.conn;
		
	}
	public void close() {
		if(this.conn!=null) {
			try {this.conn.close();}catch(SQLException e) {e.printStackTrace();}
		}
	}
}
