package com.kosmo.protest1;



import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;

import java.awt.Font;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JTextPane;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.*;
import java.awt.FlowLayout;
import javax.swing.JScrollPane;
import java.awt.Color;

public class EditPanel extends JPanel {
	private JTextField textField;
	private JTextField textField_1;
	FileInputStream fis = null; 
	FileOutputStream fos = null;
	String gubun= "1";
	FileWriter fw;
	BufferedReader br = null;
	BufferedWriter bw = null;
	private JTextField dayNumber;
	ImageIcon firstDay;
	ImageIcon secondDay;
	ImageIcon thirdDay;
	JLabel day_1;
	JLabel day_2;
	JLabel day_3;

	/**
	 * Create the panel.
	 */
	public EditPanel() {
		setBackground(Color.BLACK);

		JPanel airplanePanel = new JPanel();
		airplanePanel.setForeground(Color.WHITE);
		airplanePanel.setBackground(Color.BLACK);

		JPanel panel_1 = new JPanel();

		JPanel panel = new JPanel();
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
				groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
						.addContainerGap()
						.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(panel, GroupLayout.PREFERRED_SIZE, 503, GroupLayout.PREFERRED_SIZE)
								.addComponent(airplanePanel, GroupLayout.DEFAULT_SIZE, 503, Short.MAX_VALUE)
								.addComponent(panel_1, Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, 503, GroupLayout.PREFERRED_SIZE))
						.addContainerGap())
				);
		groupLayout.setVerticalGroup(
				groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
						.addContainerGap()
						.addComponent(airplanePanel, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE)
						.addGap(5)
						.addComponent(panel_1, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(panel, GroupLayout.DEFAULT_SIZE, 431, Short.MAX_VALUE)
						.addContainerGap())
				);

		JPanel savePanel = new JPanel();

		JScrollPane scrollPane = new JScrollPane();
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
				gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
						.addContainerGap()
						.addComponent(savePanel, GroupLayout.PREFERRED_SIZE, 476, GroupLayout.PREFERRED_SIZE)
						.addContainerGap(15, Short.MAX_VALUE))
				.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 503, Short.MAX_VALUE)
				);
		gl_panel.setVerticalGroup(
				gl_panel.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panel.createSequentialGroup()
						.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 379, Short.MAX_VALUE)
						.addPreferredGap(ComponentPlacement.UNRELATED)
						.addComponent(savePanel, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
						.addContainerGap())
				);

		JTextPane textPane = new JTextPane();
		scrollPane.setViewportView(textPane);
		savePanel.setLayout(null);

		JButton btnNewButton = new JButton("임시저장");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				gubun=dayNumber.getText();
				String line2 = textPane.getText();
				System.out.println(line2);
				try {
					File f = new File("c:\\down\\day"+gubun+".txt");
					fos = new FileOutputStream(f);
					OutputStreamWriter osw = new OutputStreamWriter(fos);
					osw.write(line2);
					osw.flush();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} finally {
					try {
						fos.close();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}   
			}
		}
				);
		btnNewButton.setBounds(0, 0, 236, 32);
		savePanel.add(btnNewButton);

		JButton btnNewButton_1 = new JButton("저장");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int YesOrNo = JOptionPane.showConfirmDialog(null, "진짜 저장해?");
				if(YesOrNo==0&&!gubun.equals("0")) {
					String line2 = textPane.getText();
					System.out.println(line2);
					try {
						fos = new FileOutputStream("c:\\down\\day"+gubun+".txt");
						OutputStreamWriter osw = new OutputStreamWriter(fos);
						osw.write(line2);
						osw.flush();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} finally {
						try {
							fos.close();
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}   
				}
			}
		});
		btnNewButton_1.setBounds(240, 0, 236, 32);
		savePanel.add(btnNewButton_1);
		panel.setLayout(gl_panel);


		firstDay = new ImageIcon("C:\\jp\\eclipseworkspace\\com.kosmo.protest\\src\\com\\kosmo\\protest1\\제1일버튼.png");
		day_1 = new JLabel(firstDay);
		day_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				firstDay = new ImageIcon("C:\\jp\\eclipseworkspace\\com.kosmo.protest\\src\\com\\kosmo\\protest1\\제1일버튼눌림.png");
				day_1.setIcon(firstDay);
			}
			@Override
			public void mouseReleased(MouseEvent e) {
				firstDay = new ImageIcon("C:\\jp\\eclipseworkspace\\com.kosmo.protest\\src\\com\\kosmo\\protest1\\제1일버튼.png");
				day_1.setIcon(firstDay);
				gubun = "1";
				dayNumber.setText(gubun);
				String linePath = "c:\\down\\day1.txt";
				String line = "";
				FileInputStream fis = null; // 지역변수이니 반드시 초기화
				try {
					fis = new FileInputStream(linePath);
					InputStreamReader isr = new InputStreamReader(fis);
					int res = 0;
					//fis.read() -> 
					while((res = isr.read())!= -1) {
						line += (char)res;
					}

				}catch(FileNotFoundException e1) {
					e1.printStackTrace();
				}catch(IOException e2) {
					e2.printStackTrace();
				}finally {
					try {
						textPane.setText(line);
						fis.close();
						System.out.println("day_1출력완료");

					}catch(IOException e3) {
						e3.printStackTrace();
					}
				}
			}
		});


		secondDay = new ImageIcon("C:\\jp\\eclipseworkspace\\com.kosmo.protest\\src\\com\\kosmo\\protest1\\제2일버튼.png");
		day_2 = new JLabel(secondDay);
		day_2.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				secondDay = new ImageIcon("C:\\jp\\eclipseworkspace\\com.kosmo.protest\\src\\com\\kosmo\\protest1\\제2일버튼눌림.png");
				day_2.setIcon(secondDay);
			}
			@Override
			public void mouseReleased(MouseEvent e) {
				secondDay = new ImageIcon("C:\\jp\\eclipseworkspace\\com.kosmo.protest\\src\\com\\kosmo\\protest1\\제2일버튼.png");
				day_2.setIcon(secondDay);
				gubun = "2";
				dayNumber.setText(gubun);
				String linePath = "c:\\down\\day2.txt";
				String line = "";
				FileInputStream fis = null; // 지역변수이니 반드시 초기화
				try {
					fis = new FileInputStream(linePath);
					InputStreamReader isr = new InputStreamReader(fis);
					int res = 0;
					//fis.read() -> 
					while((res = isr.read())!= -1) {
						line += (char)res;
					}

				}catch(FileNotFoundException e1) {
					e1.printStackTrace();
				}catch(IOException e2) {
					e2.printStackTrace();
				}finally {
					try {
						textPane.setText(line);
						fis.close();
						System.out.println("day_2출력완료");

					}catch(IOException e3) {
						e3.printStackTrace();
					}
				}
			}
		});

		thirdDay = new ImageIcon("C:\\jp\\eclipseworkspace\\com.kosmo.protest\\src\\com\\kosmo\\protest1\\제3일버튼.png");
		day_3 = new JLabel(thirdDay);
		day_3.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				thirdDay = new ImageIcon("C:\\jp\\eclipseworkspace\\com.kosmo.protest\\src\\com\\kosmo\\protest1\\제3일버튼눌림.png");
				day_3.setIcon(thirdDay);
			}
			@Override
			public void mouseReleased(MouseEvent e) {
				thirdDay = new ImageIcon("C:\\jp\\eclipseworkspace\\com.kosmo.protest\\src\\com\\kosmo\\protest1\\제3일버튼.png");
				day_3.setIcon(thirdDay);
				gubun = "3";
				dayNumber.setText(gubun);
				String linePath = "c:\\down\\day3.txt";
				String line = "";
				FileInputStream fis = null; // 지역변수이니 반드시 초기화
				try {
					fis = new FileInputStream(linePath);
					InputStreamReader isr = new InputStreamReader(fis);
					int res = 0;
					//fis.read() -> 
					while((res = isr.read())!= -1) {
						line += (char)res;
					}

				}catch(FileNotFoundException e1) {
					e1.printStackTrace();
				}catch(IOException e2) {
					e2.printStackTrace();
				}finally {
					try {
						textPane.setText(line);
						fis.close();
						System.out.println("day_3출력완료");

					}catch(IOException e3) {
						e3.printStackTrace();
					}
				}
			}
		});

		GroupLayout gl_panel_1 = new GroupLayout(panel_1);
		gl_panel_1.setHorizontalGroup(
				gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup()
						.addComponent(day_1, GroupLayout.PREFERRED_SIZE, 160, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.UNRELATED)
						.addComponent(day_2, GroupLayout.PREFERRED_SIZE, 160, GroupLayout.PREFERRED_SIZE)
						.addGap(13)
						.addComponent(day_3, GroupLayout.DEFAULT_SIZE, 160, Short.MAX_VALUE))
				);
		gl_panel_1.setVerticalGroup(
				gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
						.addComponent(day_1, GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE)
						.addComponent(day_3, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE)
						.addComponent(day_2, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE))
				);
		panel_1.setLayout(gl_panel_1);

		dayNumber = new JTextField();
		airplanePanel.add(dayNumber);
		dayNumber.setColumns(1);

		JLabel label_1 = new JLabel("일      ");
		label_1.setForeground(Color.WHITE);
		label_1.setFont(new Font("궁서체", Font.BOLD, 15));
		airplanePanel.add(label_1);

		JLabel lblNewLabel = new JLabel("비행기 일정");
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setFont(new Font("궁서체", Font.BOLD, 16));
		airplanePanel.add(lblNewLabel);

		textField = new JTextField();
		textField.setFont(new Font("궁서체", Font.BOLD, 12));
		textField.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {


			}
		});
		airplanePanel.add(textField);
		textField.setColumns(10);

		JLabel label = new JLabel("~");
		label.setForeground(Color.WHITE);
		label.setFont(new Font("궁서체", Font.BOLD, 12));
		airplanePanel.add(label);

		textField_1 = new JTextField();
		textField_1.setFont(new Font("궁서체", Font.BOLD, 12));
		airplanePanel.add(textField_1);
		textField_1.setColumns(10);

		JButton button = new JButton("검색");
		button.setFont(new Font("궁서체", Font.BOLD, 12));
		airplanePanel.add(button);
		setLayout(groupLayout);

	}
}