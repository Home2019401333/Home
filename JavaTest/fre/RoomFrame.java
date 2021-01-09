package fre;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

import dao.Add;
import dao.Check;
import dao.Export;

import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JSpinner;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
/**
 * 房间管理界面
 * @author ASUS
 *
 */
public class RoomFrame extends JInternalFrame {
	private static RoomFrame rframe = null;
	private JTextField textRoom;
	private JTextField textPrice;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static synchronized RoomFrame GetInstance() throws Exception {
		if (rframe == null) {
			rframe = new RoomFrame ();
		}
		return rframe;
	}

	/**
	 * Create the frame.
	 * @throws SQLException 
	 */
	private RoomFrame() throws SQLException {
		setClosable(true);
		setTitle("\u623F\u95F4\u4FE1\u606F\u64CD\u4F5C");
		setFrameIcon(new ImageIcon("C:\\Users\\ASUS\\Desktop\\\u65B0\u5EFA\u6587\u4EF6\u5939\\h10.png"));
		setResizable(true);
		setBounds(100, 100, 648, 547);
		getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon("C:\\Users\\ASUS\\Desktop\\\u65B0\u5EFA\u6587\u4EF6\u5939\\alogo1516.jpg"));
		lblNewLabel.setBounds(0, 0, 636, 99);
		getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("\u95E8\u724C\u53F7\uFF1A");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_1.setBounds(45, 128, 58, 15);
		getContentPane().add(lblNewLabel_1);
		
		textRoom = new JTextField();
		textRoom.setBounds(113, 125, 66, 21);
		getContentPane().add(textRoom);
		textRoom.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("\u7C7B\u578B\uFF1A");
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_2.setBounds(233, 128, 58, 15);
		getContentPane().add(lblNewLabel_2);
		
		JComboBox comKind = new JComboBox();
		comKind.setModel(new DefaultComboBoxModel(new String[] {"\u6807\u51C6\u623F", "\u7ECF\u6D4E\u623F", "\u5546\u52A1\u623F", "\u603B\u7EDF\u5957\u623F"}));
		comKind.setBounds(301, 124, 79, 23);
		getContentPane().add(comKind);
		
		JComboBox comCondition = new JComboBox();
		comCondition.setModel(new DefaultComboBoxModel(new String[] {"\u7A7A\u4F59", "\u4F4F\u6EE1"}));
		comCondition.setBounds(301, 180, 66, 23);
		getContentPane().add(comCondition);
		
		JLabel lblNewLabel_3 = new JLabel("\u4EF7\u683C\uFF1A");
		lblNewLabel_3.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_3.setBounds(45, 184, 58, 15);
		getContentPane().add(lblNewLabel_3);
		
		textPrice = new JTextField();
		textPrice.setBounds(113, 181, 66, 21);
		getContentPane().add(textPrice);
		textPrice.setColumns(10);
		
		
		JButton btnAdd = new JButton("\u589E\u52A0");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String str1=(String) comKind.getSelectedItem();//房间类型
				String str2=(String) comCondition.getSelectedItem();//房间状态
				try {
					if(Check.CheckRoomF(textRoom.getText())&&Check.CheckAny(textPrice.getText())) {
						String sql="insert into [dbo].[Room] values('"+textRoom.getText()+"','"+str1+"','"+textPrice.getText()+"','"+str2+"')";
						Add.AddText(sql);
						JOptionPane.showMessageDialog(null,"房间添加成功");
					}else {
						JOptionPane.showMessageDialog(null,"请重新操作");
					}
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				
			}
		});
		btnAdd.setBounds(491, 109, 66, 23);
		getContentPane().add(btnAdd);
		
		JButton btnReplace = new JButton("\u4FEE\u6539");
		btnReplace.addActionListener(new ActionListener() {//修改
			public void actionPerformed(ActionEvent e) {
				String str3=(String) comKind.getSelectedItem();//房间类型
				String str4=(String) comCondition.getSelectedItem();//房间状态
				try {
					if(Check.CheckRoom(textRoom.getText())&&Check.CheckAny(textPrice.getText())) {
						String sql2="update [dbo].[Room] set kind='"+str3+"',price='"+textPrice.getText()+"',condition='"+str4+"' "
								+ "where Roomid='"+textRoom.getText()+"'";
						Add.AddText(sql2);	
						JOptionPane.showMessageDialog(null,"修改成功");
					}
					else {
						JOptionPane.showMessageDialog(null,"请重新操作");
					}
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		btnReplace.setBounds(491, 156, 66, 23);
		getContentPane().add(btnReplace);
		
		JButton btnNewButton = new JButton("\u5220\u9664");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if(Check.CheckRoom(textRoom.getText())) {
						String sql3="delete from [dbo].[Room] where Roomid='"+textRoom.getText()+"' ";
						Add.AddText(sql3);	
						JOptionPane.showMessageDialog(null,"删除成功");
					}else {
						JOptionPane.showMessageDialog(null,"请重新操作");
					}
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				
			}
		});
		btnNewButton.setBounds(491, 208, 66, 23);
		getContentPane().add(btnNewButton);
		
		JLabel lblNewLabel_4 = new JLabel("\u72B6\u6001\uFF1A");
		lblNewLabel_4.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_4.setBounds(233, 184, 58, 15);
		getContentPane().add(lblNewLabel_4);
		
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(45, 271, 558, 201);
		getContentPane().add(scrollPane);
		
		table = new JTable();
		table.setEnabled(false);
		scrollPane.setViewportView(table);
		
		String sql4="select* from [dbo].[Room]";
		String[] title= {"门牌号","类型","价格 元/晚","状态"};
		

		DefaultTableModel model=Export.UpdateJtable(title,sql4);		
		TableRowSorter sorter = new TableRowSorter<DefaultTableModel>(model);//设置表格模型排序器
		table.setRowSorter(sorter);//设置表格排序器
		
		table.setAutoCreateRowSorter(true);
		table.setModel(model);
		
		JButton btnNewButton_1 = new JButton("");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					DefaultTableModel model1=Export.UpdateJtable(title,sql4);
					table.setModel(model1);
				} catch (SQLException e1) {
					e1.printStackTrace();
				}	
			}
		});
		btnNewButton_1.setHorizontalTextPosition(SwingConstants.CENTER);
		btnNewButton_1.setIcon(new ImageIcon("C:\\Users\\ASUS\\Desktop\\\u65B0\u5EFA\u6587\u4EF6\u5939\\r5.png"));
		btnNewButton_1.setBounds(575, 478, 30, 30);
		getContentPane().add(btnNewButton_1);
		
		


	}
}
