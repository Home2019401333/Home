package fre;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.SwingConstants;

import dao.Check;
import dao.Export;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.awt.Font;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
/**
 * 退房办理界面
 * @author ASUS
 *
 */
public class FinishFrame extends JInternalFrame {
	private static FinishFrame fframe = null;
	private JTextField textId;
	private JTextField textNumber;

	/**
	 * Launch the application.
	 */
	public static synchronized FinishFrame GetInstance() throws Exception {
		if (fframe == null) {
			fframe = new FinishFrame ();
		}
		return fframe;
	}

	/**
	 * Create the frame.
	 */
	private FinishFrame() {
		setClosable(true);
		setTitle("\u9000\u623F\u529E\u7406");
		setFrameIcon(new ImageIcon("C:\\Users\\ASUS\\Desktop\\\u65B0\u5EFA\u6587\u4EF6\u5939\\AWT-Passport.png"));
		setBounds(100, 100, 406, 282);
		getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("\u8EAB\u4EFD\u8BC1\u53F7\u7801\uFF1A");
		lblNewLabel.setBounds(70, 76, 84, 15);
		getContentPane().add(lblNewLabel);
		
		textId = new JTextField();
		textId.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				if(textId.getText().length()>=18) {
					e.consume();
				}
				
			}
		});
		textId.setBounds(148, 73, 125, 21);
		getContentPane().add(textId);
		textId.setColumns(10);
	
		
		JLabel lblNewLabel_1 = new JLabel("\u624B\u673A\u53F7\u7801\uFF1A");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_1.setBounds(73, 115, 67, 15);
		getContentPane().add(lblNewLabel_1);
		
		textNumber = new JTextField();
		textNumber.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				if(textNumber.getText().length()>=11) {
					e.consume();
				}
			}
		});
		textNumber.setBounds(148, 112, 125, 21);
		getContentPane().add(textNumber);
		textNumber.setColumns(10);
	
		JButton btnNewButton = new JButton("\u786E\u5B9A");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String overtime=null;
				SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				Date date=new Date();
				overtime= simpleDateFormat.format(date);
				String sql="select* from [dbo].[Person] where id='"+textId.getText()+"' and pnum='"+textNumber.getText()+"' ";				
				String sql2="update [dbo].[Person] set state='退房', overtime='"+overtime+"'"
						+ " where id='"+textId.getText()+"'";	//修改旅客信息
				String sql3="select* from [dbo].[Person] where id='"+textId.getText()+"'";
				String sql4="select* from [dbo].[Person] where id='"+textId.getText()+"' and state='入住'";				
				try {
						if(Check.CheckDr(sql4)&&Check.CheckIdDate(sql, sql2)) {
							JOptionPane.showMessageDialog(null,"退房办理成功！欢迎下次光临");
							try {
								String sql5="select name from [dbo].[Person] where id='"+textId.getText()+"'";
								ResultSet rs=Export.ExportDate(sql5);
								String txtname=null;
								while(rs.next()) {
									txtname=rs.getString("name");
								}
								Export.ExportText(sql3,txtname);
							} catch (Exception e1) {
								e1.printStackTrace();
							}
							JOptionPane.showMessageDialog(null,"账单已导出");
							textId.setText("");
							textNumber.setText("");
						}
						else {
							JOptionPane.showMessageDialog(null,"该旅客信息不存在！");
						}
				} catch ( Exception e1) {
					e1.printStackTrace();
				}
			}
		});

		btnNewButton.setBounds(158, 159, 73, 23);
		getContentPane().add(btnNewButton);
		
		JLabel lblNewLabel_2 = new JLabel("\u7559\u4E0B\u6765\u597D\u4E0D\u597D");
		lblNewLabel_2.setForeground(Color.CYAN);
		lblNewLabel_2.setFont(new Font("仿宋", Font.BOLD | Font.ITALIC, 20));
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setBounds(86, 10, 211, 31);
		getContentPane().add(lblNewLabel_2);
	}
}
