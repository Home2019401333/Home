package fre;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.SwingConstants;

import dao.Add;
import dao.Check;
import dao.Export;

import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
/**
 * 房间更换
 * @author ASUS
 *
 */
public class ChangeRoomFrame extends JInternalFrame {
	private static ChangeRoomFrame crFrame = null;
	private JTextField textId;
	private JTextField textRoom;
	private JButton btnNewButton;

	/**
	 * Launch the application.
	 */
	public static synchronized ChangeRoomFrame GetInstance() throws Exception {
		if (crFrame == null) {
			crFrame = new ChangeRoomFrame ();
		}
		return crFrame;
}

	/**
	 * Create the frame.
	 */
	public ChangeRoomFrame() {
		setClosable(true);
		setTitle("\u623F\u95F4\u66F4\u6362\u529E\u7406");
		setFrameIcon(new ImageIcon("C:\\Users\\ASUS\\Desktop\\\u65B0\u5EFA\u6587\u4EF6\u5939\\f2.png"));
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("\u8EAB\u4EFD\u8BC1\u53F7\u7801\uFF1A");
		lblNewLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel.setBounds(92, 71, 77, 15);
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
		textId.setBounds(180, 68, 120, 21);
		getContentPane().add(textId);
		textId.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("\u66F4\u6362\u540E\u95E8\u724C\u53F7\uFF1A");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_1.setBounds(69, 123, 108, 15);
		getContentPane().add(lblNewLabel_1);
		
		textRoom = new JTextField();
		textRoom.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				if(textRoom.getText().length()>=6) {
					e.consume();
				}
			}
		});
		textRoom.setBounds(183, 120, 58, 21);
		getContentPane().add(textRoom);
		textRoom.setColumns(10);
		
		btnNewButton = new JButton("\u786E\u5B9A");
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					if(Check.CheckId1(textId.getText())&&Check.CheckRoom(textRoom.getText())) {
						String sql="select Roomid from [dbo].[Person] where id='"+textId.getText()+"'";//获取之前入住房间号
						ResultSet rs=Export.ExportDate(sql);
						String all=null;
						while(rs.next()) {
							all=rs.getString("Roomid");
						}
						String sql1="update [dbo].[Room] set condition='空余' where Roomid='"+all+"'";
						String sql2="update [dbo].[Room] set condition='住满' where Roomid='"+textRoom.getText()+"'";
						String sql3="update [dbo].[Person] set Roomid='"+textRoom.getText()+"' where id='"+textId.getText()+"'"; 
						Add.AddText(sql1);
						Add.AddText(sql2);
						Add.AddText(sql3);
						JOptionPane.showMessageDialog(null,"房间更换成功！");
						textId.setText("");
						textRoom.setText("");
					}else {
						JOptionPane.showMessageDialog(null,"请如实填写信息！");
					}
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		btnNewButton.setBounds(162, 173, 64, 23);
		getContentPane().add(btnNewButton);

	}

}
