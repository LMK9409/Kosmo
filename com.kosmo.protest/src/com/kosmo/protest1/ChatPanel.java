package com.kosmo.protest1;

import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.CardLayout;

import javax.swing.JScrollPane;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.Socket;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.Font;

public class ChatPanel extends JPanel {
	private JTextField textField;
	String logid;
	String btitle;

	/**
	 * Create the panel.
	 */
	public ChatPanel(Socket sk,String logid,String btitle) {
		this.logid=logid;
		this.btitle=btitle;
		DBServiceimpl t = new DBServiceimpl();
		int bseq=t.getBseq(btitle);
		setLayout(new BorderLayout(0, 0));
		OutputStream os;
		try {
			os = sk.getOutputStream();
			PrintStream out = new PrintStream(os);
			out.println(logid);
			out.println(bseq);
		} catch (IOException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		JPanel panel = new JPanel();
		panel.setBackground(Color.BLACK);
		add(panel, BorderLayout.SOUTH);
		
		
		
		textField = new JTextField();
		textField.setFont(new Font("궁서체", Font.BOLD, 12));
		textField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {

				if(e.getKeyCode()==KeyEvent.VK_ENTER) {
					try {
						OutputStream os = sk.getOutputStream();
						PrintStream out = new PrintStream(os);
						System.out.println(textField.getText());
						out.println(textField.getText());
						textField.setText("");
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
		});
		panel.add(textField);
		textField.setColumns(20);
		
		
		JButton btnNewButton = new JButton("전송");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				try {
					OutputStream os = sk.getOutputStream();
					PrintStream out = new PrintStream(os);
					System.out.println(textField.getText());
					out.println(textField.getText());
					textField.setText("");
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}
		});
		panel.add(btnNewButton);
		
		
		
		JTextArea textArea = new JTextArea();
		textArea.setFont(new Font("궁서체", Font.BOLD, 14));
		JScrollPane scrollPane = new JScrollPane(textArea);
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		add(scrollPane, BorderLayout.CENTER);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setRowHeaderView(scrollPane_1);
		
		JTextArea textArea_1 = new JTextArea();
		textArea_1.setFont(new Font("궁서체", Font.BOLD, 13));
		textArea_1.setForeground(Color.WHITE);
		textArea_1.setBackground(Color.BLACK);
		textArea_1.setColumns(10);
		scrollPane_1.setViewportView(textArea_1);
		
		JButton button = new JButton("지우개");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textArea.setText("");
			}
			
		});
		
		panel.add(button);
		GuiRead gr = new GuiRead(sk, textArea, textArea_1,bseq);
		Thread rt=new Thread(gr);
		rt.start();
		
		

	}

}
