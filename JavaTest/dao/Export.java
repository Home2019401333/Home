package dao;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Vector;

import javax.swing.table.DefaultTableModel;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import jdbc.DatabaseConnectionSql;
/**
 *������
 * @author ASUS
 *
 */
public class Export {

	/**
	 * ִ��SQL���������ݿ����Ϣ
	 * @param sql
	 * @return ResultSet
	 * @throws SQLException
	 */
	public static ResultSet ExportDate(String sql) throws SQLException {
		ResultSet rs=null;
		DatabaseConnectionSql  dbc=new DatabaseConnectionSql();
		Connection conn=dbc.getConnection();
		Statement stm=conn.createStatement();
		rs=stm.executeQuery(sql);	
		return rs;
		
	}
	/**
	 * �������ݿ��ÿ���Ϣ
	 * @param titles
	 * @param sql
	 * @return DefaultTableModel
	 * @throws SQLException
	 */
	public static DefaultTableModel ExportDate(String[] titles,String sql) throws SQLException {//�������ݿ����Ϣ
		DefaultTableModel model=new DefaultTableModel(titles,0);
		ResultSet rs=ExportDate(sql);
		while(rs.next()) {
			Vector<Object> row=new Vector<>();
			Collections.addAll(row,rs.getString("name"),rs.getString("sex"),rs.getInt("age"),rs.getString("id"),
					rs.getString("pnum"),rs.getString("Roomid"),rs.getTimestamp("begintime"),rs.getTimestamp("overtime"),rs.getString("state"));
				model.addRow(row);
		}
		return model;
		
	
		
	}
	/**
	 * ����������Ϣ
	 * @param titles
	 * @param sql
	 * @return
	 * @throws SQLException
	 */
	public static DefaultTableModel UpdateJtable1(String[] titles,String sql) throws SQLException {
		DefaultTableModel model=new DefaultTableModel(titles, 0);
		Export export=new Export();
		ResultSet rs=export.ExportDate(sql);
		while(rs.next()){
			Vector<Object> row=new Vector<>();
			Collections.addAll(row,rs.getString("name"),rs.getString("sex"),rs.getInt("age"),rs.getString("id"),
					rs.getString("pnum"),rs.getString("Roomid"),rs.getTimestamp("begintime"));
				model.addRow(row);			
			}
			return model;	
		
	}
	
	/**
	 * ����������Ϣ����������ģ��
	 * @param titles
	 * @param sql
	 * @return
	 * @throws SQLException
	 */
	public static DefaultTableModel UpdateJtable(String[] titles,String sql) throws SQLException {
		DefaultTableModel model=new DefaultTableModel(titles, 0)
		{//ʹ��Vectorװ�ر������ģ�ͣ���дgetColumnClass������ʵ�ְ���ֵ����
			  public Class getColumnClass(int column) {
				  int row=getRowCount();				    
				    if(row==0) {
				    	return Object.class;
				    }else {
				    	 return getValueAt(0, column).getClass();
				    }
				 
				}
			  };// ����������ģ�͵ı����������(Ϊ0��)
		Export export=new Export();
		ResultSet rs=export.ExportDate(sql);
		while(rs.next()){
			Vector<Object> row=new Vector<>();
			Collections.addAll(row,rs.getString(1),rs.getString(2),rs.getInt(3),rs.getString(4));
				model.addRow(row);			
			}
			return model;	
		
	}
	
	/**
	 * ��ѯ���ݿ��ÿ���Ϣ�����ڵ���
	 * @param titles
	 * @param sql
	 * @param str
	 * @return
	 * @throws SQLException
	 */
	public static DefaultTableModel ExportDate(String[] titles,String sql,String str) throws SQLException {//�������ݿ����Ϣ
		DefaultTableModel model=new DefaultTableModel(titles,0);
		ResultSet rs=ExportDate(sql);
		SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String begintime=null;
		while(rs.next()) {
			begintime=simpleDateFormat.format(rs.getDate("begintime"));
			if(rs.getString("name").contains(str)||rs.getString("sex").contains(str)||rs.getString("age").contains(str)||rs.getString("id").contains(str)||rs.getString("pnum").contains(str)||
					rs.getString("Roomid").contains(str)||begintime.contains(str)) {
				Vector<Object> row=new Vector<>();
				Collections.addAll(row,rs.getString("name"),rs.getString("sex"),rs.getInt("age"),rs.getString("id"),
					rs.getString("pnum"),rs.getString("Roomid"),rs.getTimestamp("begintime"),rs.getTimestamp("overtime"),rs.getString("state"));
				model.addRow(row);
			}
			
		}
		return model;
		
	
		
	}
	/**
	 * ����txt�ı�
	 * @param sql
	 * @param person
	 * @throws Exception
	 */
	public static void ExportText(String sql,String person) throws Exception {
		ResultSet rs=ExportDate(sql);
		String name=null;
		String sex=null;
		int age=0;
		String id=null;
		String pnum=null;
		String Roomid=null;
		String begintime=null;
		String overtime=null;
		String state=null;
		
		File file=new File("e:/java/"+person+".txt");
		if(file.exists()) {
			file.delete();
			file.createNewFile();
		}else {
			file.createNewFile();
		}
		FileWriter wf=new FileWriter(file);
		while(rs.next()) {
			name=rs.getString("name");
			sex=rs.getString("sex");
			age=rs.getInt("age");
			id=rs.getString("id");
			pnum=rs.getString("pnum");
			Roomid=rs.getString("Roomid");
			
			SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			begintime=simpleDateFormat.format(rs.getTimestamp("begintime"));
			overtime=simpleDateFormat.format(rs.getTimestamp("overtime"));

			state=rs.getString("state");
			wf.write("������"+name);
			wf.write("\r\n");
			wf.write("�Ա�"+sex);
			wf.write("\r\n");
			wf.write("���䣺"+age);
			wf.write("\r\n");
			wf.write("���֤���룺"+id);
			wf.write("\r\n");
			wf.write("�ֻ����룺"+pnum);
			wf.write("\r\n");
			wf.write("��ס���䣺"+Roomid);	
			wf.write("\r\n");
			wf.write("��סʱ�䣺"+begintime);
			wf.write("\r\n");
			wf.write("�˷�ʱ�䣺"+overtime);
			wf.write("\r\n");
			wf.write("״̬��"+state);
			wf.write("\r\n");
			wf.write("���ѣ�****Ԫ");
			wf.write("\r\n\n");
			}
			wf.close();
		
		
		
		}
	/**
	 * ����Excel��
	 * @param sql
	 * @throws IOException
	 */
	public static  void CreateExcel(String sql) throws IOException {
		int i=0;
		String begintime=null;
		String overtime=null;
		try {
			ResultSet rs=ExportDate(sql);
			 File file=new File("e:/java/��¼.xlsx");//��λҪ������excel�ļ�
			 if(file.exists()) {
				 file.delete();
			 }
			  Workbook workbook=new XSSFWorkbook();//��������������
			  Sheet sheet = workbook.createSheet("test1");//�������������
			  Row row = sheet.createRow(i);// �����ж����±��0��ʼ
			  row.createCell(0).setCellValue("����");
			  row.createCell(1).setCellValue("�Ա�");
			  row.createCell(2).setCellValue("����");
			  row.createCell(3).setCellValue("���֤����");
			  row.createCell(4).setCellValue("�绰����");
			  row.createCell(5).setCellValue("���ƺ�");
			  row.createCell(6).setCellValue("��סʱ��");
			  row.createCell(7).setCellValue("�˷�ʱ��");
			  row.createCell(8).setCellValue("״̬");
			  while(rs.next()) {
				  Row row1= sheet.createRow(++i);// �����ж����±��0��ʼ
				  row1.createCell(0).setCellValue(rs.getString("name"));
				  row1.createCell(1).setCellValue(rs.getString("sex"));
				  row1.createCell(2).setCellValue(rs.getString("age"));
				  row1.createCell(3).setCellValue(rs.getString("id"));
				  row1.createCell(4).setCellValue(rs.getString("pnum"));
				  row1.createCell(5).setCellValue(rs.getString("Roomid"));
				  SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				  begintime=simpleDateFormat.format(rs.getTimestamp("begintime"));
				  overtime=simpleDateFormat.format(rs.getTimestamp("overtime"));
				  row1.createCell(6).setCellValue(begintime);
				  row1.createCell(7).setCellValue(overtime);
				  row1.createCell(8).setCellValue(rs.getString("state"));
			  }
			  
			  FileOutputStream fos=new FileOutputStream(file);//�������������
			  workbook.write(fos);//������д�뵽ָ����excel�ĵ�
			  fos.close();

		
		
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
		
			
			
	}


