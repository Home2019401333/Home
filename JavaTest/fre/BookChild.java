package fre;

import java.awt.EventQueue;

import javax.swing.ButtonGroup;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JRadioButton;
import javax.swing.JPasswordField;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.Vector;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

import dao.Add;
import dao.Check;
import dao.Export;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JToggleButton;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.Font;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
/**
 * 入住办理界面
 * @author ASUS
 *
 */
public class BookChild extends JInternalFrame {
	private static BookChild bframe = null;
	private JTextField txtName;
	private JTextField txtAge;
	private JPasswordField passtxtId;
	private JTextField txtNumber;
	private String Sex=null;
	private JTable table;
	private JTextField txtRoom;

	/**
	 * Launch the application.
	 */

	public static synchronized BookChild GetInstance() throws Exception {
		if (bframe == null) {
			bframe = new BookChild ();
		}
		return bframe;
}
	

	/**
	 * Create the frame.
	 * 
	 */
	private BookChild() throws Exception {
		setClosable(true);//设置关闭窗口方法
		setFrameIcon(new ImageIcon("C:\\Users\\ASUS\\Desktop\\\u65B0\u5EFA\u6587\u4EF6\u5939\\f8.png"));
		setToolTipText("");
		setTitle("\u5165\u4F4F\u529E\u7406");
		setBounds(100, 100, 756, 637);
		getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("\u59D3\u540D\uFF1A");
		lblNewLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel.setBounds(32, 50, 58, 15);
		getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("\u6027\u522B\uFF1A");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_1.setBounds(32, 98, 58, 15);
		getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("\u5E74\u9F84\uFF1A");
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_2.setBounds(32, 136, 58, 15);
		getContentPane().add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("\u8EAB\u4EFD\u8BC1\u53F7\u7801\uFF1A");
		lblNewLabel_3.setBounds(241, 50, 77, 15);
		getContentPane().add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("\u7535\u8BDD\u53F7\u7801\uFF1A");
		lblNewLabel_4.setBounds(243, 98, 75, 15);
		getContentPane().add(lblNewLabel_4);
		
		txtName = new JTextField();
		txtName.setBounds(98, 47, 66, 21);
		getContentPane().add(txtName);
		txtName.setColumns(10);
		
		JRadioButton manRadioButton = new JRadioButton("\u7537");
		manRadioButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (manRadioButton.isSelected()) {
					Sex = manRadioButton.getText();
				}
			}
		});	
		manRadioButton.setBounds(89, 94, 44, 23);	
		
		JRadioButton womanRadioButton = new JRadioButton("\u5973");
		womanRadioButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (womanRadioButton.isSelected()) {
					Sex = womanRadioButton.getText();
				}
			}
		});
		womanRadioButton.setBounds(135, 94, 44, 23);	
		
		ButtonGroup sexGroup = new ButtonGroup();//对JRadioButton进行分组操作以达到互斥效果
		sexGroup.add(manRadioButton);
		sexGroup.add(womanRadioButton);
		
		getContentPane().add(manRadioButton);
		getContentPane().add(womanRadioButton);
		
		txtAge = new JTextField();
		txtAge.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				if(txtAge.getText().length()>=3) {
					e.consume();
				}
			}
		});
		txtAge.setBounds(98, 133, 35, 21);
		getContentPane().add(txtAge);
		txtAge.setColumns(10);
		
		txtNumber = new JTextField();
		txtNumber.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {//电话号码不超过11位
				if(txtNumber.getText().length()>=11) {
					e.consume();
				}
			}
		});
		txtNumber.setBounds(328, 95, 121, 21);
		getContentPane().add(txtNumber);
		txtNumber.setColumns(10);
		
		
		
		JLabel lblNewLabel_5 = new JLabel("\u95E8\u724C\u53F7\uFF1A");
		lblNewLabel_5.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_5.setBounds(237, 136, 58, 15);
		getContentPane().add(lblNewLabel_5);
		
		txtRoom = new JTextField();
		txtRoom.setBounds(327, 133, 66, 21);
		getContentPane().add(txtRoom);
		txtRoom.setColumns(10);
		
		passtxtId = new JPasswordField();
		passtxtId.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				if(passtxtId.getText().length()>=18) {//限制身份证输入位数
					e.consume();
				}
			}
		});
		passtxtId.setBounds(328, 47, 121, 21);
		getContentPane().add(passtxtId);
		
		
		JLabel lblNewLabel_6 = new JLabel("");
		lblNewLabel_6.setIcon(new ImageIcon("C:\\Users\\ASUS\\Desktop\\\u65B0\u5EFA\u6587\u4EF6\u5939\\QQ\u5934\u50CF\u684C\u9762\u56FE\u6807\u4E0B\u8F7D14.png"));
		lblNewLabel_6.setBounds(549, 36, 137, 162);
		getContentPane().add(lblNewLabel_6);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(32, 290, 677, 262);
		getContentPane().add(scrollPane);
		
		table = new JTable();
		table.setEnabled(false);
		scrollPane.setViewportView(table);
		
		String sql="select* from [dbo].[Room]";
		String[] titles= {"门牌号","类型","价格 元/晚","状态"};
		
		DefaultTableModel model=Export.UpdateJtable(titles,sql);		
		TableRowSorter sorter = new TableRowSorter<DefaultTableModel>(model);//设置表格模型排序器
		table.setRowSorter(sorter);//设置表格排序器
		
		table.setAutoCreateRowSorter(true);	
		table.setModel(model);
		
		
	
		
		JButton btnNewButton = new JButton("\u786E\u5B9A");
		btnNewButton.addActionListener(new ActionListener() {//确认
			public void actionPerformed(ActionEvent e) {
				Check check=new Check();
				try {
					if(txtNumber.getText().length()==11 && passtxtId.getText().length()==18&& check.CheckAny(txtName.getText())&&check.CheckAny(Sex)&&check.CheckAge(txtAge.getText())&&
							check.CheckAny(txtNumber.getText())&&check.CheckId(passtxtId.getText())&&check.CheckRoom(txtRoom.getText())) {
						
						Add.AddDate(txtName.getText(), Sex, txtAge.getText(),passtxtId.getText(), txtNumber.getText(),txtRoom.getText());//Person注入信息
						String sql4="update [dbo].[Room] set condition='住满' where Roomid='"+txtRoom.getText()+"'";
						Add.AddText(sql4);//修改Room信息
						
						JOptionPane.showMessageDialog(null,"入住成功，使用身份证解锁即可入住");
						txtName.setText("");
						sexGroup.clearSelection();
						txtAge.setText("");
						txtNumber.setText("");
						passtxtId.setText("");
						txtRoom.setText("");
					}else {
						JOptionPane.showMessageDialog(null,"请如实填写相关信息");
					}
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		btnNewButton.setBounds(173, 183, 66, 23);
		getContentPane().add(btnNewButton);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"\u6240\u6709", "\u6807\u51C6\u623F", "\u7ECF\u6D4E\u623F", "\u5546\u52A1\u623F", "\u603B\u7EDF\u5957\u623F"}));
		comboBox.setBounds(135, 245, 77, 21);
		getContentPane().add(comboBox);
		
		JLabel lblNewLabel_7 = new JLabel("\u623F\u95F4\u7C7B\u578B\uFF1A");
		lblNewLabel_7.setFont(new Font("宋体", Font.PLAIN, 12));
		lblNewLabel_7.setBounds(62, 248, 76, 15);
		getContentPane().add(lblNewLabel_7);
		
		JLabel lblNewLabel_8 = new JLabel("\u623F\u95F4\u72B6\u6001\uFF1A");
		lblNewLabel_8.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_8.setBounds(241, 248, 68, 15);
		getContentPane().add(lblNewLabel_8);
		
		JComboBox comboBox2 = new JComboBox();
		comboBox2.setModel(new DefaultComboBoxModel(new String[] {"\u6240\u6709", "\u7A7A\u4F59", "\u4F4F\u6EE1"}));
		comboBox2.setBounds(319, 244, 77, 22);
		getContentPane().add(comboBox2);
		
		JButton btnUpdate = new JButton("");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String sql3="select* from [dbo].[Room]";
				try {
					DefaultTableModel model1=Export.UpdateJtable(titles,sql3);
					table.setModel(model1);
				} catch (SQLException e1) {
					e1.printStackTrace();
				}							
			}
		});
		btnUpdate.setIcon(new ImageIcon("C:\\Users\\ASUS\\Desktop\\\u65B0\u5EFA\u6587\u4EF6\u5939\\r5.png"));
		btnUpdate.setBounds(554, 245, 35, 28);
		getContentPane().add(btnUpdate);
		
		JButton btnNewButton_1 = new JButton("\u7B5B\u9009");
		btnNewButton_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String str1=(String) comboBox.getSelectedItem();//房间类型
				String str2=(String) comboBox2.getSelectedItem();//房间状态
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
					table.setModel(model2);
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		btnNewButton_1.setBounds(422, 244, 75, 23);
		getContentPane().add(btnNewButton_1);
		
		JLabel lblNewLabel_9 = new JLabel("\u4FE1\u606F\u586B\u5199\u540E\u4E0D\u80FD\u4FEE\u6539  \u8BF7\u8C28\u614E\u586B\u5199");
		lblNewLabel_9.setForeground(Color.RED);
		lblNewLabel_9.setBounds(131, 10, 216, 15);
		getContentPane().add(lblNewLabel_9);

	}
}
