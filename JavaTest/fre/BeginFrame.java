package fre;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java.awt.Window.Type;
import javax.swing.JButton;
import javax.swing.JDialog;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Toolkit;
import javax.swing.ImageIcon;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.UnsupportedLookAndFeelException;

import java.awt.Font;
/**
 * 开始界面
 * @author ASUS
 *
 */
public class BeginFrame extends JFrame{
	private JPanel contentPane;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BeginFrame frame = new BeginFrame();
					frame.setLocationRelativeTo(null);
					frame.setTitle("欢迎来到开心宾馆");
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
	public BeginFrame() {
		setResizable(false);
		setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\ASUS\\Desktop\\\u65B0\u5EFA\u6587\u4EF6\u5939\\h22.png"));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 483, 351);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btn1 = new JButton("\u7BA1\u7406\u5458\u767B\u5165");
		btn1.setForeground(Color.BLUE);
		btn1.setIcon(new ImageIcon("C:\\Users\\ASUS\\Desktop\\\u65B0\u5EFA\u6587\u4EF6\u5939\\h2.png"));
		btn1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {			
				DrFrame dframe=new DrFrame();
				dframe.setTitle("管理员登入界面");
				dframe.setLocationRelativeTo(null);//居中显示
				dframe.setVisible(true);
				
			}
		});
		btn1.setBounds(160, 209, 125, 37);
		contentPane.add(btn1);
		
		JButton btn2 = new JButton("\u65C5\u5BA2\u767B\u5165");
		btn2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					PrimaryFrame pframe=new PrimaryFrame();
					pframe.setExtendedState(JFrame.MAXIMIZED_BOTH);//窗口最大化
					pframe.setTitle("旅客中心");
					pframe.setVisible(true);
				} catch ( Exception e1) {
					e1.printStackTrace();
				
				}	
			}
		});
		btn2.setFont(new Font("宋体", Font.PLAIN, 12));
		btn2.setForeground(Color.BLUE);
		btn2.setIcon(new ImageIcon("C:\\Users\\ASUS\\Desktop\\\u65B0\u5EFA\u6587\u4EF6\u5939\\AWT-Bed.png"));
		btn2.setBounds(160, 140, 125, 37);
		contentPane.add(btn2);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon("C:\\Users\\ASUS\\Desktop\\\u65B0\u5EFA\u6587\u4EF6\u5939\\7d4209d3f711ebde209e497b2e1754b2_19515634_170713103950_2.png"));
		lblNewLabel.setBounds(10, 10, 449, 106);
		contentPane.add(lblNewLabel);
	}
}
