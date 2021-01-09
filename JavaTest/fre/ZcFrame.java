package fre;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Vo.Create;
import dao.Add;
import dao.Check;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.sql.SQLException;
import java.util.regex.Pattern;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.Toolkit;
import javax.swing.JPasswordField;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
/**
 * 注册界面
 * @author ASUS
 *
 */
public class ZcFrame extends JFrame {

	private JPanel contentPane;
	private JTextField textNewzh;
	private JTextField textnum;
	private JLabel lblNewzh;
	private JLabel lblNewnum;
	private JLabel lblNewmm_2;
	private JLabel lblNewmm_1;
	private JPasswordField textNewmm_1;
	private JPasswordField textNewmm_2;
	private JTextField textPass;
	private JLabel lblPass;
	private String strPass;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ZcFrame frame = new ZcFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public ZcFrame() {
		setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\ASUS\\Desktop\\\u65B0\u5EFA\u6587\u4EF6\u5939\\f2.png"));
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 407, 394);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("\u65B0\u8D26\u6237\uFF1A");
		lblNewLabel.setBounds(87, 79, 58, 15);
		contentPane.add(lblNewLabel);
		
		textNewzh = new JTextField();
		textNewzh.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				if(textNewzh.getText().length()>=12) {
					e.consume();
				}
			}
		});
		textNewzh.setText("\u81EA\u5B9A\u4E49\u8D26\u6237");
		textNewzh.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				textNewzh.selectAll();
			}
			@Override
			public void focusLost(FocusEvent e) {
				try {
						CheckNewZh();
				} catch ( Exception e1) {
					e1.printStackTrace();
				}
			}
			
			
		});
		textNewzh.setBounds(155, 76, 108, 21);
		contentPane.add(textNewzh);
		textNewzh.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("\u5BC6\u7801\uFF1A");
		lblNewLabel_1.setBounds(102, 122, 58, 15);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("\u91CD\u65B0\u786E\u8BA4\u5BC6\u7801\uFF1A");
		lblNewLabel_2.setBounds(56, 167, 87, 15);
		contentPane.add(lblNewLabel_2);
		
		
		JLabel lblNewLabel_3 = new JLabel("\u7535\u8BDD\u53F7\u7801\uFF1A");
		lblNewLabel_3.setBounds(75, 205, 68, 15);
		contentPane.add(lblNewLabel_3);
		
		textnum = new JTextField();
		textnum.addFocusListener(new FocusAdapter() {
			
			@Override
			public void focusLost(FocusEvent e) {
				CheckNum();
			}
			@Override
			public void focusGained(FocusEvent e) {
				textnum.selectAll();
			}
		});
		textnum.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				if(textnum.getText().length()>=11) {
					e.consume();
				}
			}
		});
		textnum.setBounds(155, 202, 108, 21);
		contentPane.add(textnum);
		textnum.setColumns(10);
		
		lblNewzh = new JLabel("");
		lblNewzh.setForeground(Color.RED);
		lblNewzh.setBounds(273, 72, 110, 42);
		contentPane.add(lblNewzh);
		
		lblNewmm_2 = new JLabel("");
		lblNewmm_2.setForeground(Color.RED);
		lblNewmm_2.setBounds(273, 161, 110, 21);
		contentPane.add(lblNewmm_2);
		
		lblNewnum = new JLabel("");
		lblNewnum.setForeground(Color.RED);
		lblNewnum.setBounds(273, 205, 100, 21);
		contentPane.add(lblNewnum);
		
		lblNewmm_1 = new JLabel("");
		lblNewmm_1.setForeground(Color.RED);
		lblNewmm_1.setBounds(273, 109, 110, 42);
		contentPane.add(lblNewmm_1);
		
		textNewmm_1 = new JPasswordField();
		textNewmm_1.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				CheckMm_1();
			}
		});
		textNewmm_1.setBounds(155, 119, 108, 21);
		contentPane.add(textNewmm_1);
		
		textNewmm_2 = new JPasswordField();
		textNewmm_2.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				 CheckMm_2();
			}
		});
		textNewmm_2.setBounds(155, 164, 108, 21);
		contentPane.add(textNewmm_2);
		
		JLabel lblNewLabel_4 = new JLabel("\u9A8C\u8BC1\u7801\uFF1A");
		lblNewLabel_4.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_4.setBounds(75, 242, 58, 15);
		contentPane.add(lblNewLabel_4);
		
		textPass = new JTextField();
		textPass.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				if(textPass.getText().length()>=4) {
					e.consume();
				}
			}
		});
		textPass.setBounds(155, 239, 66, 21);
		contentPane.add(textPass);
		textPass.setColumns(10);
		
		
		
		lblPass = new JLabel("");
		lblPass.setHorizontalAlignment(SwingConstants.CENTER);
		lblPass.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				strPass=Create.CreateNumber();
				lblPass.setText(strPass);
			}
		});
		lblPass.setForeground(Color.RED);
		lblPass.setFont(new Font("宋体", Font.PLAIN, 20));
		lblPass.setBounds(231, 232, 68, 31);
		contentPane.add(lblPass);
		strPass=Create.CreateNumber();
		lblPass.setText(strPass);
		
		JButton btnZc = new JButton("\u6CE8\u518C");
		btnZc.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if(textPass.getText().equals(strPass)) {
						if((textnum.getText().length()==11)&& CheckNewZh()&&CheckMm_1()&&CheckMm_2()&&CheckNum()) {
						String sql4="insert into [dbo].[Key](dnum,pass,numberp) values('"+textNewzh.getText()+"',"
								+ "'"+textNewmm_1.getText()+"','"+textnum.getText()+"')";
						Add.AddText(sql4);
							JOptionPane.showMessageDialog(null,"注册成功！");
							dispose();
						}else {
							JOptionPane.showMessageDialog(null,"请如实填写信息");
						}
					}else {
						JOptionPane.showMessageDialog(null,"验证码错误");
					}
					
				} catch ( Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		btnZc.setBounds(166, 278, 68, 23);
		contentPane.add(btnZc);
		
	}
	
	public boolean CheckNewZh() throws Exception {
		String sql2="select* from [dbo].[Key] where dnum='"+textNewzh.getText()+"'";
		if(textNewzh.getText().length()==0) {
			lblNewzh.setText("账户不能为空！");
			textNewzh.requestFocus();
			return false;
		}else if(!textNewzh.getText().matches("^[a-z0-9A-Z]+$")) {
			lblNewzh.setText("<html>账户不能含有字符!</html>");
			textNewzh.requestFocus();
			return false;
		}
		else if(Check.CheckDr(sql2)) {
			lblNewzh.setText("账户已存在!");
			textNewzh.requestFocus();
			textNewzh.selectAll();
			return false;
		}else if(textNewzh.getText().length()<=6) {
			lblNewzh.setText("账户必须大于6位数");
			return false;
		}else {
			lblNewzh.setText("");
			return true;
		}
		
	}
	
	public boolean CheckMm_1() {
		Pattern pattern1=Pattern.compile("[0-9]*");
		Pattern pattern2=Pattern.compile("[a-zA-Z]*");
		if(textNewmm_1.getText().length()==0) {
			lblNewmm_1.setText("密码不能为空！");
			return false;
		}else if(!textNewmm_1.getText().matches("^[a-z0-9A-Z]+$")) {
			lblNewmm_1.setText("不能含有字符！");
			return false;
		}
		else if(pattern1.matcher(textNewmm_1.getText()).matches()||pattern2.matcher(textNewmm_1.getText()).matches()) {
			lblNewmm_1.setText("<html>密码须由数字、字母混合组成!</html>");
			return false;
		}else if(textNewmm_1.getText().length()<=4) {
			lblNewmm_1.setText("密码必须大于4位数");
			return false;
		}		
		else {
			lblNewmm_1.setText("");
			return true;
		}	
		
	}
	
	public boolean CheckMm_2() {
		if(textNewmm_2.getText().length()==0) {
			lblNewmm_2.setText("不能为空！");
			return false;
		}
		else if(!textNewmm_1.getText().equals(textNewmm_2.getText())) {
			lblNewmm_2.setText("密码不一致！");
			return false;
		}
		else {
		lblNewmm_2.setText("");
		return true;
	}
	}
	
	public boolean CheckNum() {
		Pattern pattern=Pattern.compile("[0-9]*");
		if(textnum.getText().length()==0) {
			lblNewnum.setText("不能为空！");
			return false;
		}else if(!pattern.matcher(textnum.getText()).matches()) {
			lblNewnum.setText("格式错误！");
			return false;
		}else {
			lblNewnum.setText("");
			return true;
		}
	}
}
