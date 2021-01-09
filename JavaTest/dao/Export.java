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
 *导出类
 * @author ASUS
 *
 */
public class Export {

	/**
	 * 执行SQL，返回数据库表信息
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
	 * 导出数据库旅客信息
	 * @param titles
	 * @param sql
	 * @return DefaultTableModel
	 * @throws SQLException
	 */
	public static DefaultTableModel ExportDate(String[] titles,String sql) throws SQLException {//导出数据库表信息
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
	 * 导出个人信息
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
	 * 导出房间信息，设置排序模板
	 * @param titles
	 * @param sql
	 * @return
	 * @throws SQLException
	 */
	public static DefaultTableModel UpdateJtable(String[] titles,String sql) throws SQLException {
		DefaultTableModel model=new DefaultTableModel(titles, 0)
		{//使用Vector装载表格数据模型，覆写getColumnClass方法，实现按数值排序
			  public Class getColumnClass(int column) {
				  int row=getRowCount();				    
				    if(row==0) {
				    	return Object.class;
				    }else {
				    	 return getValueAt(0, column).getClass();
				    }
				 
				}
			  };// 定义表格数据模型的表格标题和行数(为0行)
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
	 * 查询数据库旅客信息，存在导出
	 * @param titles
	 * @param sql
	 * @param str
	 * @return
	 * @throws SQLException
	 */
	public static DefaultTableModel ExportDate(String[] titles,String sql,String str) throws SQLException {//导出数据库表信息
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
	 * 导出txt文本
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
			wf.write("姓名："+name);
			wf.write("\r\n");
			wf.write("性别："+sex);
			wf.write("\r\n");
			wf.write("年龄："+age);
			wf.write("\r\n");
			wf.write("身份证号码："+id);
			wf.write("\r\n");
			wf.write("手机号码："+pnum);
			wf.write("\r\n");
			wf.write("入住房间："+Roomid);	
			wf.write("\r\n");
			wf.write("入住时间："+begintime);
			wf.write("\r\n");
			wf.write("退房时间："+overtime);
			wf.write("\r\n");
			wf.write("状态："+state);
			wf.write("\r\n");
			wf.write("消费：****元");
			wf.write("\r\n\n");
			}
			wf.close();
		
		
		
		}
	/**
	 * 导出Excel表
	 * @param sql
	 * @throws IOException
	 */
	public static  void CreateExcel(String sql) throws IOException {
		int i=0;
		String begintime=null;
		String overtime=null;
		try {
			ResultSet rs=ExportDate(sql);
			 File file=new File("e:/java/记录.xlsx");//定位要操作的excel文件
			 if(file.exists()) {
				 file.delete();
			 }
			  Workbook workbook=new XSSFWorkbook();//创建工作簿对象
			  Sheet sheet = workbook.createSheet("test1");//创建工作表对象
			  Row row = sheet.createRow(i);// 创建行对象，下标从0开始
			  row.createCell(0).setCellValue("姓名");
			  row.createCell(1).setCellValue("性别");
			  row.createCell(2).setCellValue("年龄");
			  row.createCell(3).setCellValue("身份证号码");
			  row.createCell(4).setCellValue("电话号码");
			  row.createCell(5).setCellValue("门牌号");
			  row.createCell(6).setCellValue("入住时间");
			  row.createCell(7).setCellValue("退房时间");
			  row.createCell(8).setCellValue("状态");
			  while(rs.next()) {
				  Row row1= sheet.createRow(++i);// 创建行对象，下标从0开始
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
			  
			  FileOutputStream fos=new FileOutputStream(file);//创建输出流对象
			  workbook.write(fos);//将内容写入到指定的excel文档
			  fos.close();

		
		
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
		
			
			
	}


