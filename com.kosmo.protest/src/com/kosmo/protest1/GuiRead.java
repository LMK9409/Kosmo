package com.kosmo.protest1;

import java.awt.TextField;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.*;
import java.util.HashMap;
import java.util.HashSet;

import javax.swing.JTextArea;
import javax.swing.JTextField;

public class GuiRead implements Runnable{
	Socket sk;
	JTextArea chat= null;
	JTextArea nic= null;
	int bseq;

	public GuiRead(Socket sk,JTextArea chat,JTextArea nic,int bseq) {
		this.sk = sk;
		this.chat=chat;
		this.nic=nic;
		this.bseq=bseq;

	}
	@Override
	public void run() {
		try {
			InputStream is = sk.getInputStream();
			InputStreamReader isr = new InputStreamReader(is);
			BufferedReader br = new BufferedReader(isr);
			String line = "";


			while((line=br.readLine()) != null) {

				String[] res =line.split("#");

				if(res[0].equals("name")) {
					nic.setText("");
					for(int i = 0;i<res.length;i=i+3) {
						if((res.length-i)<3) {
							continue;
							
						}
						if(Integer.parseInt(res[i+1])==bseq)
						{					
							nic.append(res[i+2]+"\n");
						}
					}


				}
				else 
					if(Integer.parseInt(res[0])==bseq)
				{
					for (int i = 1; i < res.length; i++) 
					{
						chat.append(res[i]);
					}
					chat.setCaretPosition(chat.getDocument().getLength());
					chat.append("\n");
				}

			}
		} catch (IOException e) {
			// TODO Auto-generated catch block

			e.printStackTrace();
		}
	}
}


