package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;

import jdbc.DatabaseConnectionSql;
/**
 * ��Ϣ��ӵ����ݿ�
 * @author ASUS
 *
 */
public class Add {
	/**
	 * ִ��sql,ʵ������ɾ�������
	 * @param sql
	 * @throws SQLException
	 */
	public static void AddText(String sql) throws SQLException {
		DatabaseConnectionSql  dbc=new DatabaseConnectionSql();
		Connection conn=dbc.getConnection();
		Statement stm=conn.createStatement();
		stm.executeUpdate(sql);	
		
	}
	/**
	 * �����Ϣ��Person
	 * @param str1
	 * @param str2
	 * @param str3
	 * @param str4
	 * @param str5
	 * @param str6
	 * @throws SQLException
	 */
	public static void AddDate(String str1,String str2,String str3,String str4,String str5,String str6) throws SQLException {
		int age=Integer.valueOf(str3);
		String begintime=null;
		SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date=new Date();
		begintime= simpleDateFormat.format(date);
		DatabaseConnectionSql  dbc=new DatabaseConnectionSql();
		String sql="insert into [dbo].[Person](name,sex,age,id,pnum,Roomid,begintime,state) values('"+str1+"','"+str2+"','"+age+"',"
				+ "'"+str4+"','"+str5+"','"+str6+"','"+begintime+"','��ס')";
		Connection conn=dbc.getConnection();
		Statement stm=conn.createStatement();
		stm.executeUpdate(sql);	
	}

}
