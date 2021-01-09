package fre;

import java.awt.EventQueue;
import java.sql.SQLException;

import javax.swing.JInternalFrame;
import javax.swing.ImageIcon;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

import dao.Export;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
/**
 * 房间和旅客信息查询界面
 * @author ASUS
 *
 */
public class RoomSeekFrame extends JInternalFrame {
	private static RoomSeekFrame rsframe = null;
	private JTextField textRoomid;
	private JTable table1;
	private JTextField textId;

	/**
	 * Launch the application.
	 */
	public static synchronized RoomSeekFrame GetInstance() throws Exception {
		if (rsframe == null) {
			rsframe = new RoomSeekFrame ();
		}
		return rsframe;
	}

	/**
	 * Create the frame.
	 * @throws SQLException 
	 */
	private RoomSeekFrame() throws SQLException {
		setClosable(true);
		setTitle("\u4FE1\u606F\u67E5\u8BE2");
		setFrameIcon(new ImageIcon("C:\\Users\\ASUS\\Desktop\\\u65B0\u5EFA\u6587\u4EF6\u5939\\AWT-Bed.png"));
		setBounds(100, 100, 981, 606);
		getContentPane().setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(34, 306, 902, 247);
		getContentPane().add(scrollPane);
		table1=new JTable();
		scrollPane.setViewportView(table1);
		
		String sql="select* from [dbo].[Room]";
		String[] titles= {"门牌号","类型","价格 元/晚","状态"};
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setIcon(new ImageIcon("C:\\Users\\ASUS\\Desktop\\\u65B0\u5EFA\u6587\u4EF6\u5939\\404242c54d00873d2be0fc494519040b_veer-302970430.jpg"));
		lblNewLabel.setBounds(0, 0, 969, 190);
		getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("\u95E8\u724C\u53F7\u67E5\u8BE2\uFF1A");
		lblNewLabel_1.setBounds(91, 226, 84, 15);
		getContentPane().add(lblNewLabel_1);
		
		textRoomid = new JTextField();
		textRoomid.setBounds(157, 223, 66, 21);
		getContentPane().add(textRoomid);
		textRoomid.setColumns(10);
		
		JButton btnNewButton = new JButton("\u67E5\u8BE2");//查询
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String sql4="select* from [dbo].[Room] where Roomid='"+textRoomid.getText()+"'";
					DefaultTableModel model1=null;
						
						try {
							model1 = Export.UpdateJtable(titles,sql4);
						} catch (SQLException e1) {
							e1.printStackTrace();
						}
						table1.setModel(model1);
			}
		});
		btnNewButton.setHorizontalAlignment(SwingConstants.LEFT);
		btnNewButton.setBounds(233, 222, 66, 23);
		getContentPane().add(btnNewButton);
		
		JLabel lblNewLabel_2 = new JLabel("\u623F\u95F4\u7C7B\u578B\uFF1A");
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_2.setBounds(147, 281, 76, 15);
		getContentPane().add(lblNewLabel_2);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"\u6240\u6709", "\u6807\u51C6\u623F", "\u7ECF\u6D4E\u623F", "\u5546\u52A1\u623F", "\u603B\u7EDF\u5957\u623F"}));
		comboBox.setBounds(233, 273, 92, 23);
		getContentPane().add(comboBox);
		
		JLabel lblNewLabel_3 = new JLabel("\u72B6\u6001\uFF1A");
		lblNewLabel_3.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_3.setBounds(436, 281, 58, 15);
		getContentPane().add(lblNewLabel_3);
		
		JComboBox comboBox_1 = new JComboBox();
		comboBox_1.setModel(new DefaultComboBoxModel(new String[] {"\u6240\u6709", "\u7A7A\u4F59", "\u4F4F\u6EE1"}));
		comboBox_1.setBounds(504, 273, 66, 23);
		getContentPane().add(comboBox_1);
		
		JButton btnNewButton_1 = new JButton("");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					DefaultTableModel model1=Export.UpdateJtable(titles,sql);
					table1.setModel(model1);
				} catch (SQLException e1) {
					e1.printStackTrace();
				}	
			}
		});
		btnNewButton_1.setIcon(new ImageIcon("C:\\Users\\ASUS\\Desktop\\\u65B0\u5EFA\u6587\u4EF6\u5939\\r5.png"));
		btnNewButton_1.setBounds(769, 264, 33, 32);
		getContentPane().add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("\u7B5B\u9009");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String str1=(String) comboBox.getSelectedItem();//房间类型
				String str2=(String) comboBox_1.getSelectedItem();//房间状态
				String sql2=null;
				if(str1.contains("所有")&&str2.contains("所有")) {
					sql2="select* from[dbo].[Room]";
				}else if(str1.contains("所有")&&!str2.contains("所有")) {
					sql2="select* from[dbo].[Room] where condition='"+str2+"'";
				}else if(!str1.contains("所有")&&str2.contains("所有")) {
					sql2="select* from[dbo].[Room] where kind='"+str1+"'";
				}else {
					sql2="select* from[dbo].[Room] where kind='"+str1+"'and condition='"+str2+"'";
				}
				try {
					DefaultTableModel model2=Export.UpdateJtable(titles,sql2);
					table1.setModel(model2);
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		btnNewButton_2.setBounds(607, 273, 66, 23);
		getContentPane().add(btnNewButton_2);
		
		String sql5="select* from [dbo].[Room]";
		String[] title= {"门牌号","类型","价格 元/晚","状态"};
		
		DefaultTableModel model=Export.UpdateJtable(title,sql5);		
		TableRowSorter sorter = new TableRowSorter<DefaultTableModel>(model);//设置表格模型排序器
		table1.setRowSorter(sorter);//设置表格排序器
		
		table1.setAutoCreateRowSorter(true);
		table1.setModel(model);
		
		JLabel lblNewLabel_4 = new JLabel("\u8EAB\u4EFD\u8BC1\u53F7\u7801\uFF1A");
		lblNewLabel_4.setBounds(377, 226, 76, 15);
		getContentPane().add(lblNewLabel_4);
		
		textId = new JTextField();
		textId.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				if(textId.getText().length()>=18) {
					e.consume();
				}
			}
		});
		textId.setBounds(452, 223, 84, 21);
		getContentPane().add(textId);
		textId.setColumns(10);
		
		JButton btnNewButton_3 = new JButton("\u67E5\u8BE2");
		btnNewButton_3.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String[] titles2= {"姓名","性别","年龄","身份证号码","手机号码","门牌号","入住时间"};
				String sql6="select name,sex,age,id,pnum,Roomid,begintime from Person where id='"+textId.getText()+"' and state='入住'";
				try {
					DefaultTableModel model2=Export.UpdateJtable1(titles2, sql6);
					table1.setModel(model2);
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		btnNewButton_3.setBounds(562, 222, 66, 23);
		getContentPane().add(btnNewButton_3);

	}
}
