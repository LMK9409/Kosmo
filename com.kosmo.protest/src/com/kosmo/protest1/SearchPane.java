package com.kosmo.protest1;



import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JTextField;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JSplitPane;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

//import com.kosmo.gui.demo.GoogleMapIOTest;
import com.teamdev.jxbrowser.chromium.Browser;
import com.teamdev.jxbrowser.chromium.swing.BrowserView;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.event.PopupMenuListener;
import javax.swing.event.PopupMenuEvent;
import java.awt.Color;
import java.awt.Font;
//import net.sourceforge.jcalendarbutton.*;

public class SearchPane extends JPanel {
	private JTextField searchTextField;
	JPanel searchpane;
	JButton searchButton;
	ImageIcon icon;
	ImageIcon resizeicon;
	JLabel mapLabel;
	Browser browser;
	BrowserView view;
	private JSplitPane splitPane;
	private JPanel panel;
	private JPanel panel_1;
	String city = "Fukuoka";

	public SearchPane() {
		setLayout(new BorderLayout(0, 0));
		setPreferredSize(new Dimension(800, 1000));
		
		searchpane = new JPanel();
		searchpane.setBackground(Color.BLACK);
		add(searchpane, BorderLayout.NORTH);
		
		searchTextField = new JTextField();
		searchTextField.setFont(new Font("궁서체", Font.BOLD, 13));
		searchTextField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_ENTER) {
					city = searchTextField.getText();
					browser.loadURL("https://www.google.com/maps/search/" + city);
					searchTextField.setText("");
				}
			}
		});
		searchpane.add(searchTextField);
		searchTextField.setColumns(20);
		
		searchButton = new JButton("검색");
		searchButton.setBackground(Color.BLACK);
		searchButton.setFont(new Font("궁서체", Font.BOLD, 12));
		searchButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				city = searchTextField.getText();
				browser.loadURL("https://www.google.com/maps/search/" + city);
				searchTextField.setText("");
			}
		});
		searchpane.add(searchButton);
		
		splitPane = new JSplitPane();
		splitPane.setBackground(Color.BLACK);
		splitPane.setResizeWeight(0.7);
		splitPane.setOrientation(JSplitPane.VERTICAL_SPLIT);
		add(splitPane, BorderLayout.CENTER);
		
		panel = new JPanel();
		panel.setBackground(Color.BLACK);
		splitPane.setLeftComponent(panel);

		
		browser = new Browser();
		view = new BrowserView(browser);
		view.setBackground(Color.WHITE);
		browser.loadURL("https://www.google.com/maps/search/" + city);
		panel.setLayout(new BorderLayout(0, 0));
		
		panel.add(view);
		
		panel_1 = new JPanel();
		panel_1.setBackground(Color.BLACK);
		splitPane.setRightComponent(panel_1);
	}

}