package Vo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import jdbc.DatabaseConnectionSql;

/**
 * һ������������
 * @author ASUS
 *
 */
public class DateAdd {
	
	public static void main(String[] args) {
		Add();
	}
	
	public static int getNum(int start,int end) {//������ط���ָ����Χ�������
    	//Math.random()�������0.0��1.0֮�����
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
	
	public static void Add() {//�����˻�
    	DatabaseConnectionSql dbcs=new DatabaseConnectionSql();
    	String sql="insert into [dbo].[Key](dnum,pass,numberp) values(?,?,?)";
    	try(Connection conn=dbcs.getConnection();
    		PreparedStatement pstmt=conn.prepareStatement(sql);){//ʵ����PreparedStatement
        	ArrayList<String> alist=new ArrayList<String>();//���弯��
    		for(int i=1;i<=10000;) {
    			String dnum=getStunoNum("21").toString();//�����ȡdhum
    			String numberp=getStunoNum("137520").toString();
    			if(!alist.contains(dnum)&&!alist.contains(numberp)) {//�ж�dnum�Ƿ�Ψһ
    				alist.add(dnum);
    				String pass=getStunoNum("s21").toString();	
    				pstmt.setString(1, dnum);//�����1��ռλ��������
    	    		pstmt.setString(2, pass);//�����2��ռλ��������
    	    		pstmt.setString(3,numberp);
    	    		pstmt.addBatch();//����������ȴ�ִ��
    				i++;//
    			}
    		}
    		pstmt.executeBatch();//����ִ�в������
    		JOptionPane.showMessageDialog(null, "sucess");
    	}catch(SQLException e) {
    		e.printStackTrace();
    	}
    }
}
