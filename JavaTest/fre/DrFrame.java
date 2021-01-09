package fre;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Vo.Create;
import dao.Check;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.regex.Pattern;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import javax.swing.ImageIcon;
import java.awt.Toolkit;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.SwingConstants;
/**
 * 管理者登入界面
 * @author ASUS
 *
 */
public class DrFrame extends JFrame {

	private JPanel contentPane;
	private JTextField textzh;
	private JPasswordField textmm;
	private JLabel lblzh;
	private JLabel lblmm;
	private JTextField textPass;
	private String strPass;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DrFrame frame = new DrFrame();
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
	public DrFrame() {
		setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\ASUS\\Desktop\\\u65B0\u5EFA\u6587\u4EF6\u5939\\f3.png"));
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 436, 475);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("\u8D26\u6237\uFF1A");
		lblNewLabel.setFont(new Font("楷体", Font.PLAIN, 14));
		lblNewLabel.setBounds(100, 77, 56, 28);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("\u5BC6\u7801\uFF1A");
		lblNewLabel_1.setFont(new Font("楷体", Font.PLAIN, 14));
		lblNewLabel_1.setBounds(100, 132, 51, 28);
		contentPane.add(lblNewLabel_1);
		
		textzh = new JTextField();
		textzh.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				if(textzh.getText().length()>=12) {
					e.consume();
				}
			}
		});
		textzh.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				CheckInputZh();
			}
			@Override
			public void focusGained(FocusEvent e) {
				textzh.selectAll();
			}
		});
		textzh.setBounds(150, 81, 112, 21);
		contentPane.add(textzh);
		textzh.setColumns(10);
		
		textmm = new JPasswordField();
		textmm.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				if(textmm.getText().length()>=16) {
					e.consume();
				}
			}
		});
		textmm.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				CheckInputMm();
			}
			@Override
			public void focusGained(FocusEvent e) {
				textmm.selectAll();
			}
		});
		textmm.setBounds(150, 136, 112, 21);
		contentPane.add(textmm);
		
		
		
		JButton btnNewButton_1 = new JButton("\u5FD8\u8BB0\u5BC6\u7801");
		btnNewButton_1.setFont(new Font("楷体", Font.PLAIN, 14));
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				UpFrame upframe=new UpFrame();
				upframe.setTitle("找回密码");
				upframe.setLocationRelativeTo(null);
				upframe.setVisible(true);
			}
		});

		btnNewButton_1.setForeground(Color.BLACK);
		btnNewButton_1.setBounds(159, 405, 103, 23);
		contentPane.add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("\u540E\u9000");
		btnNewButton_2.setFont(new Font("楷体", Font.PLAIN, 14));
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnNewButton_2.setForeground(Color.BLACK);
		btnNewButton_2.setBounds(10, 405, 103, 23);
		contentPane.add(btnNewButton_2);
	
		JButton btnNewButton_3 = new JButton("\u6CE8\u518C\u65B0\u7528\u6237");
		btnNewButton_3.setFont(new Font("楷体", Font.PLAIN, 14));
		btnNewButton_3.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				ZcFrame zcframe=new ZcFrame();
				zcframe.setTitle("注册新用户");
				zcframe.setLocationRelativeTo(null);
				zcframe.setVisible(true);
			}
		});
		btnNewButton_3.setForeground(Color.BLACK);
		btnNewButton_3.setBounds(300, 405, 112, 23);
		contentPane.add(btnNewButton_3);
		
		lblzh = new JLabel("");
		lblzh.setForeground(Color.RED);
		lblzh.setBounds(272, 84, 136, 21);
		contentPane.add(lblzh);
		
	    lblmm = new JLabel("");
		lblmm.setForeground(Color.RED);
		lblmm.setBounds(272, 132, 145, 46);
		contentPane.add(lblmm);
		
		JLabel lblNewLabel_2 = new JLabel("\u9A8C\u8BC1\u7801\uFF1A");
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setFont(new Font("楷体", Font.PLAIN, 14));
		lblNewLabel_2.setForeground(new Color(0, 0, 0));
		lblNewLabel_2.setBounds(93, 185, 58, 15);
		contentPane.add(lblNewLabel_2);
		
		textPass = new JTextField();
		textPass.setBounds(150, 182, 56, 21);
		contentPane.add(textPass);
		textPass.setColumns(10);
		
		
		JLabel lblpass = new JLabel("");
		lblpass.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				strPass=new Create().CreateNumber();
				lblpass.setText(strPass);
			}
		});
		lblpass.setFont(new Font("宋体", Font.PLAIN, 20));
		lblpass.setForeground(Color.RED);
		lblpass.setHorizontalAlignment(SwingConstants.CENTER);
		lblpass.setBounds(205, 175, 72, 31);
		contentPane.add(lblpass);
		
		strPass=new Create().CreateNumber();
		lblpass.setText(strPass);
		
		JButton btnNewButton = new JButton("");
		btnNewButton.setIcon(new ImageIcon("C:\\Users\\ASUS\\Desktop\\\u65B0\u5EFA\u6587\u4EF6\u5939\\k10.png"));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(textPass.getText().equals(strPass)) {
					if(CheckInputZh()&&CheckInputMm()) {
						try {
							String sql="select* from [dbo].[Key] where dnum='"+textzh.getText()+"'and pass='"+textmm.getText()+"'";
							if(Check.CheckDr(sql)){
								try {
									ManagerFrame mframe=new ManagerFrame();
									mframe.setExtendedState(JFrame.MAXIMIZED_BOTH);//窗口最大化
									mframe.setTitle("管理中心");
									mframe.setVisible(true);
								} catch ( Exception e1) {
									e1.printStackTrace();
								
								}	
							}
							else {
								JOptionPane.showMessageDialog(null, "账户或密码错误！");
							}
						} catch (Exception e1) {
							e1.printStackTrace();
						}
					}
				}else {
					JOptionPane.showMessageDialog(null,"验证码错误");
				}
					
			}
		});
		btnNewButton.setBounds(170, 229, 66, 28);
		contentPane.add(btnNewButton);
	}
	public  boolean CheckInputZh() {
		if(textzh.getText().length()==0) {
			lblzh.setText("账户不能为空!");
			return false;
		}else if(!textzh.getText().matches("^[a-z0-9A-Z]+$")) {
			lblzh.setText("账户必须为数字、字母!");
			textzh.requestFocus();
			textzh.selectAll();
			return false;
		}else {
			lblzh.setText("");
			return true;
		}	
		
	}
	public  boolean CheckInputMm() {
		Pattern pattern1=Pattern.compile("[0-9]*");
		Pattern pattern2=Pattern.compile("[a-zA-Z]*");
		if(textmm.getText().length()==0) {
			lblmm.setText("密码不能为空！");
			return false;
		}else if(!textmm.getText().matches("^[a-z0-9A-Z]+$")) {
			lblmm.setText("密码只能为数字、字母!");
			textmm.selectAll();
			return false;
		}else if(pattern1.matcher(textmm.getText()).matches()||pattern2.matcher(textmm.getText()).matches()) {
			lblmm.setText("<html>密码须由数字、字母混合组成!</html>");
			return false;
		}else {
			lblmm.setText("");
			return true;
		}
		
		
	}
	
}
