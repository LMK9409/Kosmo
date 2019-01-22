package com.kosmo.protest1;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;
import java.awt.FlowLayout;
import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import java.awt.event.MouseMotionAdapter;
import java.util.ArrayList;
import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;
import java.awt.Toolkit;

public class RoomList extends JFrame {
	static RoomList frame;
	private JPanel contentPane;
	DefaultListModel listModel;
	String logid;
	private JList list_1;
	String btitle;
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
	public RoomList(String logid) {
		this.logid=logid;
		setIconImage(Toolkit.getDefaultToolkit().getImage(RoomList.class.getResource("/com/kosmo/protest1/frameicon.jpg")));
		setTitle("三 日 天 下 -방 목록");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 722, 526);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));


		//반복문써서 db방목록 갯수만큼 add해주면됨 
		//이름써있는곳에 시퀸스.방제목을 넣으면 될듯함
		 DBServiceimpl t = new DBServiceimpl();
		listModel = new DefaultListModel();
		ArrayList<RoomVO> list = new ArrayList<RoomVO>();
		list=t.roomList();
		for(RoomVO i : list)
		{
			listModel.addElement(i.getB_title());
		}
		list_1 = new JList(listModel);
		list_1.setFont(new Font("궁서체", Font.BOLD, 16));
		list_1.setForeground(Color.WHITE);
		list_1.setBackground(Color.BLACK);
		list_1.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		list_1.setSelectedIndex(0);
		list_1.setVisibleRowCount(5);
		JScrollPane scrollPane = new JScrollPane(list_1);
		scrollPane.setViewportView(list_1);
		contentPane.add(scrollPane, BorderLayout.CENTER);
		JPanel panel = new JPanel();
		panel.setBackground(Color.BLACK);
		contentPane.add(panel, BorderLayout.SOUTH);
		
		JLabel btnNewButton_1 = new JLabel("");
		btnNewButton_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				btnNewButton_1.setIcon(new ImageIcon(RoomList.class.getResource("/com/kosmo/protest1/접속버튼눌림.png")));
				panel.add(btnNewButton_1);
			}
			@Override
			public void mouseReleased(MouseEvent e) {
				btnNewButton_1.setIcon(new ImageIcon(RoomList.class.getResource("/com/kosmo/protest1/접속버튼.png")));
				panel.add(btnNewButton_1);
				btitle=(String)list_1.getSelectedValue();
				System.out.println(btitle);
				MainFrame mf = new MainFrame(logid,btitle);
				mf.setVisible(true);
				dispose();
				
				//여기다가 방들어가지는거 작성 작성할때 방의시퀸스를 넘기면 됨
				//시퀸스는 메인프레인에서 받음
				//메인프레인에서 채팅패널 로 갈때 시퀸스에대한 채팅방으로 가짐
				//list_1.getSelectedIndex() 이게 시퀸스일거임
				
			}
		});
		btnNewButton_1.setIcon(new ImageIcon(RoomList.class.getResource("/com/kosmo/protest1/접속버튼.png")));
		panel.add(btnNewButton_1);
		
		JLabel btnNewButton = new JLabel("");
		btnNewButton.setIcon(new ImageIcon(RoomList.class.getResource("/com/kosmo/protest1/생성버튼.png")));
		panel.add(btnNewButton);
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
				RoomCreate asd = new RoomCreate(logid,listModel);
				asd.setVisible(true);
				//생성 버튼누르면 방제목 입력 프레임을 띄움
				//방제목 쓰고 생성누르면 방제목 중복검사후에 중복이없으면
				//현재방개수+1의 시퀸스를 갖고 개설함
			}
		});
		
		

	}

}
