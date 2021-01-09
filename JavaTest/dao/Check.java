package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.regex.Pattern;

import javax.swing.JOptionPane;

import jdbc.DatabaseConnectionSql;
/**
 * �ж���
 * @author ASUS
 *
 */
public class Check {
		/**
		 * ִ�в�ѯ�����ڷ���true�������ڣ�����false
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
		 * �ж��˻������Ƿ���ȷ����������
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
		 * �ж�str�Ƿ�Ϊ��
		 * @param str
		 * @return
		 */
		public static boolean CheckAny(String str) {//�ж��Ƿ�Ϊ��
			if(str.length()==0) {
				return false;
			}else {
				return true;
			}
		}
		/**
		 * �ж������Ƿ����18���Ƿ�Ϊ�գ����Ϸ���true
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
				JOptionPane.showMessageDialog(null,"δ�������޷�������ס��");
				return false;
			}else {
				return true;
			}
			
		}
		/**
		 * ��ס�жϣ��жϷ���״̬������Ϊ�գ�����true
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
				 if(rs.getString(4).contains("ס��")) {
					 JOptionPane.showMessageDialog(null,"�÷�������ס��");
					 return false;
				 }else {
					 return true; 
				 }
			}else {
				JOptionPane.showMessageDialog(null,"��������Ч���ƺţ�");
				return false; 
			}
		}
		/**
		 * ��������жϣ��жϷ����Ƿ���ڣ������ڷ���true
		 * @param str
		 * @return
		 * @throws SQLException
		 */
		public static boolean CheckRoomF(String str) throws SQLException {//��ӷ����ж�
			Connection conn=null;
			ResultSet rs=null;
			DatabaseConnectionSql  dbc=new DatabaseConnectionSql();
			String sql="select* from [dbo].[Room] where Roomid='"+str+"'";
			conn=dbc.getConnection();
			Statement stm=conn.createStatement();
			 rs=stm.executeQuery(sql);
			 if(rs.next()) {
				 JOptionPane.showMessageDialog(null,"�����Ѵ���");
				 return false;
			 }else {
				 return true;
			 }		 
			
		}
		/**
		 * �ж�Id,Id���ݿ��в����ڡ���Ϊ�գ�����true
		 * @param id
		 * @return
		 * @throws SQLException
		 */
		public static boolean CheckId(String id) throws SQLException {//��ס--id�Ƿ�Ϊ��
			Connection conn=null;
			ResultSet rs=null;
			DatabaseConnectionSql  dbc=new DatabaseConnectionSql();
			String sql="select* from [dbo].[Person] where id='"+id+"' and state='��ס'";
			conn=dbc.getConnection();
			Statement stm=conn.createStatement();
			 rs=stm.executeQuery(sql);
			if(id.length()==0) {
				return false;
			}else if(rs.next()){
				JOptionPane.showMessageDialog(null,"�����֤�����Ѵ��ڣ�");
				return false;
			}else {
				return true;
			}
		}
		/**
		 * �ж�Id,Id���ݿ��д��ڡ���Ϊ�գ�����true
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
			}else if(rs.next()){//���֤������ڷ���true
				return true;
			}else {
				return false;
			}
		}
		/**
		 * �˷���Ϣ��֤���޸ĸ��˺ͷ�����Ϣ
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
				String sql3="update [dbo].[Room] set condition='����' where Roomid='"+rs.getString("Roomid")+"'";		
				stm.executeUpdate(sql2);
				stm.executeUpdate(sql3);
				return true;
			}else {
				return false;
			}
		}
		
	}

	


