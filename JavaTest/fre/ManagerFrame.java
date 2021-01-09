package fre;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Graphics;

import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Vo.ReCenter;
import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.JDesktopPane;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;
import java.awt.event.KeyEvent;
import java.io.File;
import java.sql.SQLException;
import java.awt.event.InputEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.beans.PropertyVetoException;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
/**
 * 管理者中心界面
 * @author ASUS
 *
 */
public class ManagerFrame extends JFrame {

	private JPanel contentPane;
	private JDesktopPane desktopPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ManagerFrame frame = new ManagerFrame();
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
	 */
	public ManagerFrame() {
		setTitle("\u7BA1\u7406\u8005\u4E2D\u5FC3");
		setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\ASUS\\Desktop\\\u65B0\u5EFA\u6587\u4EF6\u5939\\h26.png"));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 453, 294);
		
		desktopPane = new JDesktopPane(){
			@Override
			public void paintComponent(Graphics g) {//重绘面板背景
				//创建一个未初始化的图像图标
				ImageIcon icon=new ImageIcon("sources"+File.separator+"907ab0a53c2a5bf960eb9f9971ffe154_t01504933f58d3f60bb.jpg");
				//绘制指定图像中已缩放到适合指定矩形内部的图像
				g.drawImage(icon.getImage(), 0, 0, this.getWidth(), this.getHeight(),this);
			}
		};

		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnNewMenu = new JMenu("\u4FE1\u606F\u7BA1\u7406");
		menuBar.add(mnNewMenu);
		
		JMenuItem mntmNewMenuItem = new JMenuItem("\u623F\u95F4\u4FE1\u606F\u6DFB\u52A0\u3001\u5220\u9664\u548C\u4FEE\u6539");
		mntmNewMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {	
					displayJInternalFrame(RoomFrame.GetInstance());
				} catch (Exception e1) {				
					e1.printStackTrace();
				}
			}
		});
		mntmNewMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Z, InputEvent.CTRL_MASK));
		mnNewMenu.add(mntmNewMenuItem);
		
		JMenuItem mntmNewMenuItem_1 = new JMenuItem("\u623F\u95F4\u548C\u65C5\u5BA2\u4FE1\u606F\u67E5\u8BE2");
		mntmNewMenuItem_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {	
					displayJInternalFrame(RoomSeekFrame.GetInstance());
				} catch (Exception e1) {				
					e1.printStackTrace();
				}
			}
		});
		
		mntmNewMenuItem_1.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A, InputEvent.ALT_MASK));
		mnNewMenu.add(mntmNewMenuItem_1);
		
		JMenu mnNewMenu_1 = new JMenu("\u65C5\u5BA2\u8BB0\u5F55");
		menuBar.add(mnNewMenu_1);
		
		JMenuItem mntmNewMenuItem_3 = new JMenuItem("\u65C5\u5BA2\u4FE1\u606F\u548C\u5386\u53F2\u8BB0\u5F55");
		mntmNewMenuItem_3.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_D, InputEvent.CTRL_MASK));
		mntmNewMenuItem_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {	
					displayJInternalFrame(PersonFrame.GetInstance());
				} catch (Exception e1) {				
					e1.printStackTrace();
				}
			}
		});
		mnNewMenu_1.add(mntmNewMenuItem_3);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		contentPane.add(desktopPane, BorderLayout.CENTER);
	}
	/**
	 * 窗口判断，单例模式
	 * @param jif
	 */
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
