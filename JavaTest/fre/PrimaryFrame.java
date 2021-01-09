package fre;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Graphics;

import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Vo.ReCenter;

import javax.swing.JToolBar;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import java.awt.Toolkit;
import java.io.File;
import java.sql.SQLException;

import javax.swing.ImageIcon;
import javax.swing.JDesktopPane;
import java.awt.event.ActionListener;
import java.beans.PropertyVetoException;
import java.awt.event.ActionEvent;
/**
 * 旅客中心界面
 * @author ASUS
 *
 */
public class PrimaryFrame extends JFrame {

	private JPanel contentPane;
	private JDesktopPane desktopPane;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PrimaryFrame frame = new PrimaryFrame();
					frame.setExtendedState(JFrame.MAXIMIZED_BOTH);//窗口最大化
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * @throws UnsupportedLookAndFeelException 
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 * @throws ClassNotFoundException 
	 */
	public PrimaryFrame() throws ClassNotFoundException, InstantiationException, IllegalAccessException, UnsupportedLookAndFeelException {
		setResizable(false);
		setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\ASUS\\Desktop\\\u65B0\u5EFA\u6587\u4EF6\u5939\\h22.png"));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 452, 304);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JToolBar toolBar = new JToolBar();
		contentPane.add(toolBar, BorderLayout.NORTH);
		
		 desktopPane = new JDesktopPane() {
			@Override
			public void paintComponent(Graphics g) {//重绘面板背景
				//创建一个未初始化的图像图标
				ImageIcon icon=new ImageIcon("sources"+File.separator+"69e3c90d050361c9af1b3d7fe3bf6f87_3-160324153120.jpg");
				//绘制指定图像中已缩放到适合指定矩形内部的图像
				g.drawImage(icon.getImage(), 0, 0, this.getWidth(), this.getHeight(),this);
			}
		};
		
		JButton btnNewButton = new JButton("\u529E\u7406\u5165\u4F4F");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					try {	
						displayJInternalFrame(BookChild.GetInstance());
					} catch (Exception e1) {				
						e1.printStackTrace();
					}	
					
			}
			
		});
		toolBar.add(btnNewButton);

		JButton btnNewButton_1 = new JButton("\u4FE1\u606F\u67E5\u8BE2");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {	
					displayJInternalFrame(RoomSeekFrame.GetInstance());
				} catch (Exception e1) {				
					e1.printStackTrace();
				}
			}
		});
		toolBar.add(btnNewButton_1);
	
		JButton btnNewButton_2 = new JButton("\u9000\u623F\u529E\u7406");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {	
					displayJInternalFrame(FinishFrame.GetInstance());
				} catch (Exception e1) {				
					e1.printStackTrace();
				}
			}
		});
		toolBar.add(btnNewButton_2);
		
		JButton btnNewButton_3 = new JButton("\u623F\u95F4\u66F4\u6362");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {	
					displayJInternalFrame(ChangeRoomFrame.GetInstance());
				} catch (Exception e1) {				
					e1.printStackTrace();
				}
			}
		});
		toolBar.add(btnNewButton_3);
		
		contentPane.add(desktopPane, BorderLayout.CENTER);
	}
	public void displayJInternalFrame(JInternalFrame jif) {
		JInternalFrame jifl[]=desktopPane.getAllFrames();
		//判断顶层面板中是否已经存在需要调用的子窗体
		if(jifl.length>0) 
			return;
		desktopPane.add(jif);//将子窗体加入主窗体的顶层面板中
		jif.setVisible(true);
		ReCenter.setFrameCenter(jif);//居中
		try {
			jif.setSelected(true);
		} catch (PropertyVetoException e1) {
			e1.printStackTrace();
		}
	}
}
