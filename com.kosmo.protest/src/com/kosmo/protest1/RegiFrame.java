package com.kosmo.protest1;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Color;
import java.awt.Font;
import javax.swing.ImageIcon;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Toolkit;

public class RegiFrame extends JFrame {

	private JPanel contentPane;
	private JTextField idText;
	private JTextField passText;
	private JTextField nameText;
	private JTextField mailText;
	private JTextField birthText;
	private JTextField qText;
	private JTextField aText;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RegiFrame frame = new RegiFrame();
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
	public RegiFrame() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(RegiFrame.class.getResource("/com/kosmo/protest1/frameicon.jpg")));
		setTitle("三 日 天 下 -회원가입");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 452, 392);
		contentPane = new JPanel();
		contentPane.setBackground(Color.BLACK);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		idText = new JTextField();
		idText.setFont(new Font("궁서체", Font.BOLD, 13));
		idText.setBounds(138, 40, 141, 21);
		contentPane.add(idText);
		idText.setColumns(10);
		
		passText = new JTextField();
		passText.setFont(new Font("궁서체", Font.BOLD, 13));
		passText.setBounds(138, 71, 141, 21);
		contentPane.add(passText);
		passText.setColumns(10);
		
		nameText = new JTextField();
		nameText.setFont(new Font("궁서체", Font.BOLD, 13));
		nameText.setBounds(138, 102, 141, 21);
		contentPane.add(nameText);
		nameText.setColumns(10);
		
		mailText = new JTextField();
		mailText.setFont(new Font("궁서체", Font.BOLD, 13));
		mailText.setBounds(138, 133, 141, 21);
		contentPane.add(mailText);
		mailText.setColumns(10);
		
		birthText = new JTextField();
		birthText.setToolTipText("ex)19940918 (94년9월18일)");
		birthText.setFont(new Font("궁서체", Font.BOLD, 13));
		birthText.setBounds(138, 164, 141, 21);
		contentPane.add(birthText);
		birthText.setColumns(10);
		
		JLabel createBtn = new JLabel("생성");
		createBtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				createBtn.setIcon(new ImageIcon(RegiFrame.class.getResource("/com/kosmo/protest1/생성버튼눌림.png")));
				createBtn.setBounds(12, 268, 199, 57);
			}
			@Override
			public void mouseReleased(MouseEvent e) {
				createBtn.setIcon(new ImageIcon(RegiFrame.class.getResource("/com/kosmo/protest1/생성버튼.png")));
				createBtn.setBounds(12, 268, 199, 57);
				//insert into 머시기 
				DBServiceimpl t = new DBServiceimpl();
				if(t.idck(idText.getText())==true)
				{
					t.memberInsert(idText.getText(), passText.getText(), nameText.getText(), mailText.getText(), birthText.getText(), qText.getText(), aText.getText());
					JOptionPane.showMessageDialog(null, "생성이 완료되었습니다");
				}else{
					JOptionPane.showMessageDialog(null, "이미 존재하는 아이디입니다");
				}
				dispose();
			}
		});
		createBtn.setIcon(new ImageIcon(RegiFrame.class.getResource("/com/kosmo/protest1/생성버튼.png")));
		createBtn.setBounds(12, 268, 199, 57);
		contentPane.add(createBtn);
		
		JLabel resetBtn = new JLabel("초기화");
		resetBtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				resetBtn.setIcon(new ImageIcon(RegiFrame.class.getResource("/com/kosmo/protest1/초기화버튼눌림.png")));
				resetBtn.setBounds(225, 266, 199, 57);
			}
			@Override
			public void mouseReleased(MouseEvent e) {
				resetBtn.setIcon(new ImageIcon(RegiFrame.class.getResource("/com/kosmo/protest1/초기화버튼.png")));
				resetBtn.setBounds(225, 266, 199, 57);
				idText.setText("");
				nameText.setText("");
				passText.setText("");
				mailText.setText("");
				birthText.setText("");
				aText.setText("");
				qText.setText("");
			}
		});
		resetBtn.setIcon(new ImageIcon(RegiFrame.class.getResource("/com/kosmo/protest1/초기화버튼.png")));
		resetBtn.setBounds(225, 266, 199, 57);
		contentPane.add(resetBtn);
		
		qText = new JTextField();
		qText.setToolTipText("암호찾기시에 필요하니 왠만하면 입력하세요 답변은 공백으로할 경우에 암호찾기시에도 공백으로해주세요");
		qText.setFont(new Font("궁서체", Font.BOLD, 13));
		qText.setBounds(138, 195, 141, 21);
		contentPane.add(qText);
		qText.setColumns(10);
		
		aText = new JTextField();
		aText.setFont(new Font("궁서체", Font.BOLD, 13));
		aText.setBounds(138, 226, 141, 21);
		contentPane.add(aText);
		aText.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("계       정");
		lblNewLabel.setFont(new Font("궁서체", Font.BOLD, 14));
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setBounds(41, 43, 110, 15);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("암       호");
		lblNewLabel_1.setForeground(Color.WHITE);
		lblNewLabel_1.setFont(new Font("궁서체", Font.BOLD, 14));
		lblNewLabel_1.setBounds(41, 74, 110, 15);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("성       명");
		lblNewLabel_2.setForeground(Color.WHITE);
		lblNewLabel_2.setFont(new Font("궁서체", Font.BOLD, 14));
		lblNewLabel_2.setBounds(41, 105, 110, 15);
		contentPane.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("전 자 우 편");
		lblNewLabel_3.setFont(new Font("궁서체", Font.BOLD, 14));
		lblNewLabel_3.setForeground(Color.WHITE);
		lblNewLabel_3.setBounds(41, 136, 110, 15);
		contentPane.add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("생 년 월 일");
		lblNewLabel_4.setFont(new Font("궁서체", Font.BOLD, 14));
		lblNewLabel_4.setForeground(Color.WHITE);
		lblNewLabel_4.setBounds(41, 167, 110, 15);
		contentPane.add(lblNewLabel_4);
		
		JLabel lblNewLabel_5 = new JLabel("암호찾기질문");
		lblNewLabel_5.setFont(new Font("궁서체", Font.BOLD, 14));
		lblNewLabel_5.setForeground(Color.WHITE);
		lblNewLabel_5.setBounds(41, 198, 110, 15);
		contentPane.add(lblNewLabel_5);
		
		JLabel lblNewLabel_6 = new JLabel("암호찾기답변");
		lblNewLabel_6.setFont(new Font("궁서체", Font.BOLD, 14));
		lblNewLabel_6.setForeground(Color.WHITE);
		lblNewLabel_6.setBounds(41, 229, 110, 15);
		contentPane.add(lblNewLabel_6);
		
		JLabel label = new JLabel("계정정보입력");
		label.setFont(new Font("궁서체", Font.BOLD, 14));
		label.setForeground(Color.WHITE);
		label.setBounds(155, 10, 124, 15);
		contentPane.add(label);
	}
}
