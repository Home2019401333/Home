package Vo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import jdbc.DatabaseConnectionSql;

/**
 * 一万条数据增加
 * @author ASUS
 *
 */
public class DateAdd {
	
	public static void main(String[] args) {
		Add();
	}
	
	public static int getNum(int start,int end) {//随机返回返回指定范围间的整数
    	//Math.random()随机返回0.0至1.0之间的数
        return (int)(Math.random()*(end-start+1)+start);
    }
	

	
	public static StringBuilder getStunoNum(String str) {
	    StringBuilder num=new StringBuilder(str);
	    StringBuilder num1=new StringBuilder(String.valueOf(getNum(1,99999)));
	    if(num1.length()==1) {
	    	num1=num1.insert(0, "0000");
	    	num=num.append(num1);
	    }else if(num1.length()==2) {
	    	num1=num1.insert(0, "000");
	    	num=num.append(num1);
	    }else if(num1.length()==3) {
	    	num1=num1.insert(0, "00");
	    	num=num.append(num1);
	    }else if(num1.length()==4) {
	    	num1=num1.insert(0, "0");
	    	num=num.append(num1);
	    }else {
	    	num=num.append(num1);
	    }
	    return num;
	   }
	
	public static void Add() {//增加账户
    	DatabaseConnectionSql dbcs=new DatabaseConnectionSql();
    	String sql="insert into [dbo].[Key](dnum,pass,numberp) values(?,?,?)";
    	try(Connection conn=dbcs.getConnection();
    		PreparedStatement pstmt=conn.prepareStatement(sql);){//实例化PreparedStatement
        	ArrayList<String> alist=new ArrayList<String>();//定义集合
    		for(int i=1;i<=10000;) {
    			String dnum=getStunoNum("21").toString();//随机获取dhum
    			String numberp=getStunoNum("137520").toString();
    			if(!alist.contains(dnum)&&!alist.contains(numberp)) {//判断dnum是否唯一
    				alist.add(dnum);
    				String pass=getStunoNum("s21").toString();	
    				pstmt.setString(1, dnum);//定义第1个占位符的内容
    	    		pstmt.setString(2, pass);//定义第2个占位符的内容
    	    		pstmt.setString(3,numberp);
    	    		pstmt.addBatch();//加入批处理等待执行
    				i++;//
    			}
    		}
    		pstmt.executeBatch();//批量执行插入操作
    		JOptionPane.showMessageDialog(null, "sucess");
    	}catch(SQLException e) {
    		e.printStackTrace();
    	}
    }
}
