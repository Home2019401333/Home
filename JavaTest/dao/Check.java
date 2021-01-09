package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.regex.Pattern;

import javax.swing.JOptionPane;

import jdbc.DatabaseConnectionSql;
/**
 * 判断类
 * @author ASUS
 *
 */
public class Check {
		/**
		 * 执行查询，存在返回true，不存在，返回false
		 * @param sql
		 * @return 
		 * @throws Exception
		 */
		public static boolean CheckDr(String sql) throws Exception {
			Connection conn=null;
			ResultSet rs=null;
			DatabaseConnectionSql  dbc=new DatabaseConnectionSql();
			conn=dbc.getConnection();
			Statement stm=conn.createStatement();
			 rs=stm.executeQuery(sql);
			if(rs.next()) {
				return true; 
			}else {
				return false; 
			}
			
		}
		

		/**
		 * 判断账户号码是否正确，重置密码
		 * @param str
		 * @param all
		 * @return
		 * @throws SQLException
		 */
		public static boolean CheckUp(String str,String all) throws SQLException {
			Connection conn=null;
			ResultSet rs=null;
			DatabaseConnectionSql  dbc=new DatabaseConnectionSql();
			String sql="select* from [dbo].[Key] where dnum='"+str+"'and numberp='"+all+"'";
			String sql2="update [dbo].[Key] set pass='1234567' where dnum='"+str+"'";
			conn=dbc.getConnection();
			Statement stm=conn.createStatement();
			 rs=stm.executeQuery(sql);
			if(rs.next()) {
				stm.executeUpdate(sql2);
				return true; 
			}else {
				return false; 
			}
		}
		/**
		 * 判断str是否为空
		 * @param str
		 * @return
		 */
		public static boolean CheckAny(String str) {//判断是否为空
			if(str.length()==0) {
				return false;
			}else {
				return true;
			}
		}
		/**
		 * 判断年龄是否大于18，是否为空，符合返回true
		 * @param str
		 * @return
		 */
		public static boolean CheckAge(String str) {
			int age=Integer.valueOf(str);
			Pattern pattern=Pattern.compile("[0-9]*");
			if(str.length()==0) {
				return false;
			}else if(!pattern.matcher(str).matches()) {
				return false;
			}else if(age<18) {
				JOptionPane.showMessageDialog(null,"未成年人无法办理入住！");
				return false;
			}else {
				return true;
			}
			
		}
		/**
		 * 入住判断，判断房间状态，存在为空，返回true
		 * @param str
		 * @return
		 * @throws SQLException
		 */
		public static boolean CheckRoom(String str) throws SQLException {
			Connection conn=null;
			ResultSet rs=null;
			DatabaseConnectionSql  dbc=new DatabaseConnectionSql();
			String sql="select* from [dbo].[Room] where Roomid='"+str+"'";
			conn=dbc.getConnection();
			Statement stm=conn.createStatement();
			 rs=stm.executeQuery(sql);
			 if(rs.next()) {
				 if(rs.getString(4).contains("住满")) {
					 JOptionPane.showMessageDialog(null,"该房间已入住！");
					 return false;
				 }else {
					 return true; 
				 }
			}else {
				JOptionPane.showMessageDialog(null,"请输入有效门牌号！");
				return false; 
			}
		}
		/**
		 * 房间添加判断，判断房间是否存在，不存在返回true
		 * @param str
		 * @return
		 * @throws SQLException
		 */
		public static boolean CheckRoomF(String str) throws SQLException {//添加房间判断
			Connection conn=null;
			ResultSet rs=null;
			DatabaseConnectionSql  dbc=new DatabaseConnectionSql();
			String sql="select* from [dbo].[Room] where Roomid='"+str+"'";
			conn=dbc.getConnection();
			Statement stm=conn.createStatement();
			 rs=stm.executeQuery(sql);
			 if(rs.next()) {
				 JOptionPane.showMessageDialog(null,"房间已存在");
				 return false;
			 }else {
				 return true;
			 }		 
			
		}
		/**
		 * 判断Id,Id数据库中不存在、不为空，返回true
		 * @param id
		 * @return
		 * @throws SQLException
		 */
		public static boolean CheckId(String id) throws SQLException {//入住--id是否为空
			Connection conn=null;
			ResultSet rs=null;
			DatabaseConnectionSql  dbc=new DatabaseConnectionSql();
			String sql="select* from [dbo].[Person] where id='"+id+"' and state='入住'";
			conn=dbc.getConnection();
			Statement stm=conn.createStatement();
			 rs=stm.executeQuery(sql);
			if(id.length()==0) {
				return false;
			}else if(rs.next()){
				JOptionPane.showMessageDialog(null,"此身份证号码已存在！");
				return false;
			}else {
				return true;
			}
		}
		/**
		 * 判断Id,Id数据库中存在、不为空，返回true
		 * @param id
		 * @return
		 * @throws SQLException
		 */
		public static boolean CheckId1(String id) throws SQLException {
			Connection conn=null;
			ResultSet rs=null;
			DatabaseConnectionSql  dbc=new DatabaseConnectionSql();
			String sql="select* from [dbo].[Person] where id='"+id+"'";
			conn=dbc.getConnection();
			Statement stm=conn.createStatement();
			 rs=stm.executeQuery(sql);
			if(id.length()==0) {
				return false;
			}else if(rs.next()){//身份证号码存在返回true
				return true;
			}else {
				return false;
			}
		}
		/**
		 * 退房信息认证并修改个人和房间信息
		 * @param sql
		 * @param sql2
		 * @return
		 * @throws SQLException
		 */
		public static boolean CheckIdDate(String sql,String sql2) throws SQLException {
			Connection conn=null;
			ResultSet rs=null;
			DatabaseConnectionSql  dbc=new DatabaseConnectionSql();
			conn=dbc.getConnection();
			Statement stm=conn.createStatement();
			rs=stm.executeQuery(sql);
			if(rs.next()) {
				String sql3="update [dbo].[Room] set condition='空余' where Roomid='"+rs.getString("Roomid")+"'";		
				stm.executeUpdate(sql2);
				stm.executeUpdate(sql3);
				return true;
			}else {
				return false;
			}
		}
		
	}

	


