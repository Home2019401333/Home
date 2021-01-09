package fre;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.HeadlessException;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import dao.Check;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.awt.Toolkit;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
/**
 * 密码重置界面
 * @author ASUS
 *
 */
public class UpFrame extends JFrame {

	private JPanel contentPane;
	private JTextField textzh;
	private JTextField textnum;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UpFrame frame = new UpFrame();					
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
	public UpFrame() {
		setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\ASUS\\Desktop\\\u65B0\u5EFA\u6587\u4EF6\u5939\\h26.png"));
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 359, 487);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("\u8D26\u6237\uFF1A");
		lblNewLabel.setBounds(73, 100, 58, 15);
		contentPane.add(lblNewLabel);
		
		textzh = new JTextField();
		textzh.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				if(textzh.getText().length()>=16) {
					e.consume();
				}
			}
		});
		textzh.setBounds(141, 97, 110, 21);
		contentPane.add(textzh);
		textzh.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("\u7535\u8BDD\u53F7\u7801\uFF1A");
		lblNewLabel_1.setBounds(60, 160, 71, 15);
		contentPane.add(lblNewLabel_1);
		
		textnum = new JTextField();
		textnum.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				if(textnum.getText().length()>=11) {
					e.consume();
				}
			}
		});
		textnum.setBounds(141, 157, 110, 21);
		contentPane.add(textnum);
		textnum.setColumns(10);
		
		JButton btnNewButton = new JButton("\u786E\u8BA4");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if(Check.CheckUp(textzh.getText(),textnum.getText())) {
						JOptionPane.showMessageDialog(null,"初始化密码：1234567");
						dispose();
					
					}else {
						JOptionPane.showMessageDialog(null,"账户或电话号码错误！");
					}
				} catch (HeadlessException e1) {					
					e1.printStackTrace();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				
				
			}
		});
		btnNewButton.setBounds(141, 234, 71, 23);
		contentPane.add(btnNewButton);
	}
}
