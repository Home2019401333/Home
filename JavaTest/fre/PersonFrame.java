package fre;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.ImageIcon;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

import dao.Add;
import dao.Check;
import dao.Export;

import java.awt.Color;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
/**
 * 旅客记录界面
 * @author ASUS
 *
 */
public class PersonFrame extends JInternalFrame {
	private static PersonFrame perframe = null;
	private JTextField textField;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static synchronized PersonFrame GetInstance() throws Exception {
		if (perframe == null) {
			perframe = new PersonFrame ();
		}
		return perframe;
	}

	/**
	 * Create the frame.
	 * @throws SQLException 
	 */
	private PersonFrame() throws SQLException {
		setClosable(true);
		setFrameIcon(new ImageIcon("C:\\Users\\ASUS\\Desktop\\\u65B0\u5EFA\u6587\u4EF6\u5939\\h2.png"));
		setTitle("\u65C5\u5BA2\u4FE1\u606F");
		setBounds(100, 100, 989, 521);
		getContentPane().setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(20, 151, 924, 281);
		getContentPane().add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		String[] titles= {"姓名","性别","年龄","身份证号码","手机号码","门牌号","入住时间","退房时间","状态"};
		String sql="select* from [dbo].[Person]";
		DefaultTableModel model=Export.ExportDate(titles, sql);	
		
		table.setAutoCreateRowSorter(true);
		table.setModel(model);
		
		
		String sql2="select count (*) from Room where condition='住满'";
		JLabel lblPerNum = new JLabel("");
		lblPerNum.setFont(new Font("宋体", Font.PLAIN, 26));
		lblPerNum.setHorizontalAlignment(SwingConstants.CENTER);
		lblPerNum.setForeground(Color.RED);
		lblPerNum.setBounds(737, 84, 77, 26);
		getContentPane().add(lblPerNum);
		
		ResultSet rs=Export.ExportDate(sql2);
		while(rs.next()) {
			lblPerNum.setText(rs.getString(1));
		}
	

		
		JLabel lblNewLabel = new JLabel("\u5173\u952E\u5B57\u67E5\u8BE2\uFF1A");
		lblNewLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel.setBounds(92, 47, 89, 26);
		getContentPane().add(lblNewLabel);
		
		textField = new JTextField();
		textField.setBounds(190, 48, 66, 25);
		getContentPane().add(textField);
		textField.setColumns(10);
		
		JButton btnNewButton = new JButton("\u67E5\u8BE2");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(textField.getText().length()==0) {
					JOptionPane.showMessageDialog(null,"请输入查询信息");
				}else {
					String sql3="select* from [dbo].[Person]";
					try {
						DefaultTableModel model1=Export.ExportDate(titles, sql3,textField.getText());
						table.setModel(model1);
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
				}
				
				
			}
		});
		btnNewButton.setBounds(297, 45, 72, 30);
		getContentPane().add(btnNewButton);
		
		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					DefaultTableModel model3=Export.ExportDate(titles, sql);
					table.setModel(model3);
				} catch (SQLException e1) {
					e1.printStackTrace();
				}	
			}
		});
		lblNewLabel_1.setIcon(new ImageIcon("C:\\Users\\ASUS\\Desktop\\\u65B0\u5EFA\u6587\u4EF6\u5939\\r8.png"));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setBounds(546, 10, 120, 120);
		getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel_3 = new JLabel("\u72B6\u6001\uFF1A");
		lblNewLabel_3.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_3.setBounds(92, 126, 58, 15);
		getContentPane().add(lblNewLabel_3);
		
		JComboBox comState = new JComboBox();
		comState.setModel(new DefaultComboBoxModel(new String[] {"\u6240\u6709", "\u5373\u65F6\u8BB0\u5F55", "\u5386\u53F2\u8BB0\u5F55"}));
		comState.setBounds(160, 122, 96, 23);
		getContentPane().add(comState);
		
		JButton btnNewButton_1 = new JButton("\u7B5B\u9009");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String str=(String) comState.getSelectedItem();
				String sql4=null;
				if(str.contains("所有")) {
					sql4="select* from [dbo].[Person]";
				}else if(str.contains("即时记录")) {
					sql4="select* from [dbo].[Person] where state='入住'";
				}else {
					sql4="select* from [dbo].[Person] where state='退房'";
				}
				DefaultTableModel model2;
				try {
					model2 = Export.ExportDate(titles, sql4,textField.getText());
					table.setModel(model2);
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				
			}
		});
		btnNewButton_1.setBounds(297, 122, 80, 23);
		getContentPane().add(btnNewButton_1);
		
		JLabel lblNewLabel_4 = new JLabel("\u5165\u4F4F\u4EBA\u6570");
		lblNewLabel_4.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_4.setFont(new Font("宋体", Font.PLAIN, 15));
		lblNewLabel_4.setBounds(727, 43, 87, 30);
		getContentPane().add(lblNewLabel_4);
		
		JButton btnNewButton_2 = new JButton("txt\u5BFC\u51FA\u5386\u53F2\u8BB0\u5F55");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Check ck=new Check();
				String sql5="select* from [dbo].[Person] where state='退房'";
				try {
					if(ck.CheckDr(sql5)) {
						Export.ExportText(sql5, "历史记录");
						JOptionPane.showMessageDialog(null,"历史记录已导出，请到e:/java 查看");
					}else {
						JOptionPane.showMessageDialog(null,"无历史记录");
					}
					
					
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		btnNewButton_2.setBounds(786, 442, 158, 23);
		getContentPane().add(btnNewButton_2);
		
		JButton btnNewButton_3 = new JButton("\u5220\u9664");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int count=table.getSelectedRow();//获取你选中的行号（记录）
				String getId= table.getValueAt(count, 3).toString();//读取你获取行号的Id
				String getRoomid=table.getValueAt(count, 5).toString();//获取房间号Roomid
				String getState=table.getValueAt(count, 8).toString();//状态state
				String sql6="delete from Person where id='"+getId+"'";
				String sql7="update Room set condition='空余' where Roomid='"+getRoomid+"'";
				try {
					Add.AddText(sql6);
					if(getState.contains("入住")) {
						Add.AddText(sql7);		
					}
					JOptionPane.showMessageDialog(null,"删除成功");
					
					ResultSet rs=Export.ExportDate(sql2);
					while(rs.next()) {
						lblPerNum.setText(rs.getString(1));
					}
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				
				
				
			
			}
		});
		btnNewButton_3.setForeground(Color.RED);
		btnNewButton_3.setBounds(867, 122, 77, 23);
		getContentPane().add(btnNewButton_3);
		
		JButton btnNewButton_4 = new JButton("Excel\u5BFC\u51FA\u5386\u53F2\u8BB0\u5F55");
		btnNewButton_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String sql8="select* from Person where state='退房'";
				try {
					Export.CreateExcel(sql8);
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		});
		btnNewButton_4.setBounds(617, 442, 159, 23);
		getContentPane().add(btnNewButton_4);
		
	}
}
