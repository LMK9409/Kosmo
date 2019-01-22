package com.kosmo.protest1;



import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;



import java.awt.Color;

import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Toolkit;
import javax.swing.JList;

public class LoginFrame extends JFrame{
	BufferedImage img = null;
	private JTextField textField;
	private JTextField textField_1;
	ImageIcon login;
	ImageIcon login2;
	JLabel login_button;
	ImageIcon member;
	ImageIcon member2;
	JLabel member_button;
	static LoginFrame frame;
	String logid;
	public static void main(String[] args) {
		frame = new LoginFrame();
	}
	public LoginFrame() {
		DBServiceimpl t = new DBServiceimpl();
		setIconImage(Toolkit.getDefaultToolkit().getImage(LoginFrame.class.getResource("/com/kosmo/protest1/frameicon.jpg")));
		setTitle("三 日 天 下");
		// 배경 Panel
		JLayeredPane layeredPane = new JLayeredPane();
		layeredPane.setLocation(0, 0);
		layeredPane.setSize(706, 498);
		layeredPane.setLayout(null);

		try {
			img = ImageIO.read(new File("C:\\jp\\eclipseworkspace\\com.kosmo.protest\\src\\com\\kosmo\\protest1\\배경.jpg"));
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "이미지 불러오기 실패");
			System.exit(0);
		}
		// 이미지 삽입
		myPanel panel = new myPanel();
		panel.setSize(700,700);
		layeredPane.add(panel);

		getContentPane().setLayout(null);

		JLabel lblNewLabel_1 = new JLabel("암   호");
		lblNewLabel_1.setForeground(Color.RED);
		lblNewLabel_1.setBounds(195, 157, 96, 32);
		getContentPane().add(lblNewLabel_1);
		lblNewLabel_1.setFont(new Font("궁서체", Font.PLAIN, 27));

		JLabel lblNewLabel = new JLabel("성   명");
		lblNewLabel.setForeground(Color.RED);
		lblNewLabel.setBounds(195, 113, 96, 32);
		getContentPane().add(lblNewLabel);
		lblNewLabel.setFont(new Font("궁서체", Font.PLAIN, 27));


		textField = new JTextField();
		textField.setBounds(300, 111, 128, 32);
		layeredPane.add(textField);
		textField.setColumns(10);

		textField_1 = new JTextField();
		textField_1.setBounds(300, 160, 128, 32);
		layeredPane.add(textField_1);
		textField_1.setColumns(10);

		member_button = new JLabel(new ImageIcon(LoginFrame.class.getResource("/com/kosmo/protest1/등록버튼.png")));
		member_button.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				member = new ImageIcon(LoginFrame.class.getResource("/com/kosmo/protest1/등록눌림.png"));
				member_button.setBounds(231, 408, 230, 80);
				member_button.setIcon(member);
			}
			@Override
			public void mouseReleased(MouseEvent e) {
				member = new ImageIcon(LoginFrame.class.getResource("/com/kosmo/protest1/등록버튼.png"));
				member_button.setBounds(231, 408, 230, 80);
				member_button.setIcon(member);
				RegiFrame rf = new RegiFrame();
				rf.setVisible(true);

			}
		});


		login_button = new JLabel(new ImageIcon(LoginFrame.class.getResource("/com/kosmo/protest1/접속버튼.png")));
		login_button.setBounds(0, 408, 230, 80);
		getContentPane().add(login_button);
		login_button.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				login = new ImageIcon(LoginFrame.class.getResource("/com/kosmo/protest1/접속버튼눌림.png"));
				login_button.setBounds(0, 408, 230, 80);
				login_button.setIcon(login);
			}
			@Override
			public void mouseReleased(MouseEvent e) {

				login = new ImageIcon(LoginFrame.class.getResource("/com/kosmo/protest1/접속버튼.png"));
				login_button.setBounds(0, 408, 230, 80);
				login_button.setIcon(login);
				if(t.idpwck(textField.getText(),textField_1.getText())==true)
				{
					logid=textField.getText();
					RoomList rl = new RoomList(logid);
					rl.setVisible(true);
					dispose();
				}else {
					JOptionPane.showMessageDialog(null, "계정과 암호가 맞지않습니다.");
				}

			}
		});
		login_button.setBackground(Color.WHITE);
		member_button.setBounds(231, 408, 230, 80);
		getContentPane().add(member_button);

		DefaultListModel<String> model = new DefaultListModel<String>();
		model.addElement("사  장 : 원치현");
		model.addElement("개발자 : 이문경");
		model.addElement("개돼지 : 김대형");


		JList list = new JList(model);
		list.setForeground(Color.RED);
		list.setFont(new Font("궁서체", Font.PLAIN, 13));
		list.setBounds(579, 425, 125, 60);
		getContentPane().add(list);
		getContentPane().add(layeredPane);




		setBounds(720, 500, 722, 526);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}

	class myPanel extends JPanel {
		public void paint(Graphics g) {
			g.drawImage(img, 0, 0, null);
		}
	}
}