package com.kosmo.protest1;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JSplitPane;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MainFrame extends JFrame {

	private JPanel contentPane;
	static MainFrame frame;
	Socket sk=null;
	String logid;
	String btitle;
	ImageIcon escapeIcon;
	JLabel button;
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
	public MainFrame(String logid,String btitle) {
		try {
			this.logid=logid;
			this.btitle=btitle;
			sk=new Socket("192.168.0.106",7777);

			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			setBounds(100, 100, 1800, 900);
			contentPane = new JPanel();
			contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
			contentPane.setLayout(new BorderLayout(0, 0));
			setContentPane(contentPane);

			JSplitPane splitPane = new JSplitPane();
			contentPane.add(splitPane, BorderLayout.CENTER);
			ChatPanel chatpanel = new ChatPanel(sk,logid,btitle);
			splitPane.setLeftComponent(chatpanel);

			JSplitPane splitPane_1 = new JSplitPane();
			splitPane.setRightComponent(splitPane_1);

			escapeIcon = new ImageIcon("C:\\jp\\eclipseworkspace\\com.kosmo.protest\\src\\com\\kosmo\\protest1\\탈출버튼.png");
			
			JPanel panel = new JPanel();
			panel.setSize(new Dimension(500, 500));
			chatpanel.add(panel, BorderLayout.NORTH);
			button = new JLabel(escapeIcon);
			panel.add(button);
			
			button.addMouseListener(new MouseAdapter() {
				@Override
				public void mousePressed(MouseEvent e) {
					escapeIcon = new ImageIcon("C:\\jp\\eclipseworkspace\\com.kosmo.protest\\src\\com\\kosmo\\protest1\\탈출버튼눌림.png");
					button.setIcon(escapeIcon);
				}
				@Override
				public void mouseReleased(MouseEvent e) {
					escapeIcon = new ImageIcon("C:\\jp\\eclipseworkspace\\com.kosmo.protest\\src\\com\\kosmo\\protest1\\탈출버튼.png");
					button.setIcon(escapeIcon);
					try {
						sk.close();
						LoginFrame lf = new LoginFrame();
						lf.setVisible(true);
						dispose();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			});


			EditPanel editpanel = new EditPanel();
			splitPane_1.setRightComponent(editpanel);

			SearchPane searchPane = new SearchPane();
			splitPane_1.setLeftComponent(searchPane);
			splitPane.setDividerLocation(500);
			splitPane_1.setDividerLocation(720);
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
