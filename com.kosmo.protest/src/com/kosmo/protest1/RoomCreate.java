package com.kosmo.protest1;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;

import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class RoomCreate extends JFrame {

	private JPanel contentPane;
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public RoomCreate(String logid,DefaultListModel listModel) {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 132);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.BLACK);
		contentPane.add(panel, BorderLayout.CENTER);
		
		JLabel lblNewLabel = new JLabel("방제목");
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setFont(new Font("궁서체", Font.BOLD, 15));
		panel.add(lblNewLabel);
		
		textField = new JTextField();
		textField.setFont(new Font("궁서체", Font.BOLD, 15));
		panel.add(textField);
		textField.setColumns(15);
		
		JLabel btnNewButton = new JLabel("");
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				btnNewButton.setIcon(new ImageIcon(RoomList.class.getResource("/com/kosmo/protest1/생성버튼눌림.png")));
				panel.add(btnNewButton);
			}
			@Override
			public void mouseReleased(MouseEvent e) {
				btnNewButton.setIcon(new ImageIcon(RoomList.class.getResource("/com/kosmo/protest1/생성버튼.png")));
				panel.add(btnNewButton);
				DBServiceimpl t = new DBServiceimpl();
				if(t.roomck(textField.getText())==true)
				{
					t.roomInsert(textField.getText(), logid);
					JOptionPane.showMessageDialog(null, "방이 생성되었습니다.");
					ArrayList<RoomVO> list = new ArrayList<RoomVO>();
					list=t.roomList();
					listModel.addElement(textField.getText());
					dispose();
	
				}else {
					JOptionPane.showMessageDialog(null, "이미 존재하는 방제목입니다.");
				}
			}
		});
		btnNewButton.setIcon(new ImageIcon(RoomCreate.class.getResource("/com/kosmo/protest1/생성버튼.png")));
		panel.add(btnNewButton);
	}

}
