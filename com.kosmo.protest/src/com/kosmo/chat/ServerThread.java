package com.kosmo.chat;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PipedOutputStream;
import java.io.PrintStream;
import java.net.Socket;
import java.util.HashMap;
import java.util.Iterator;

public class ServerThread implements Runnable{
	Socket sk;
	HashMap map;
	
	public ServerThread(Socket sk,HashMap map) {
		this.sk=sk;
		this.map=map;
	}

	@Override
	public void run() {
		BufferedReader br =null;
		PrintStream out= null;
		InputStream is=null;
		OutputStream os = null;
		String nic=null;
		String btitle=null;
		try {
			is=sk.getInputStream();
			InputStreamReader isr = new InputStreamReader(is);
			br = new BufferedReader(isr);
			
			nic=br.readLine();
			btitle=br.readLine();


			map.put(btitle+"#"+nic, sk);
			String list2="name#";
			Iterator it = map.keySet().iterator();
			
			while(it.hasNext()) {
				list2=list2+it.next()+"#name#";
			}
			sendAllMsg(list2);
			
			sendAllMsg(btitle+"#"+"	<"+nic+"님이 입장하셨습니다>");
			
			
			String msg = "";
			while((msg=br.readLine())!=null) {
				sendAllMsg(btitle+"#"+nic+" : "+msg);
			}
		}catch(IOException e) {
			e.printStackTrace();
		}finally {
			try {
				sendAllMsg(btitle+"#"+"	<"+nic+"님이 퇴장하셨습니다>");
				
				map.remove(btitle+"#"+nic, sk);
				String list2="name#";
				Iterator it = map.keySet().iterator();
				
				while(it.hasNext()) {
					list2=list2+it.next()+"#name#";
				}
				sendAllMsg(list2);
				br.close();
				sk.close();
			} catch (IOException e) {

				e.printStackTrace();
			}
		}
		
	}
	
	
	
	public void sendAllMsg(String msg) {
		try{
		Iterator it = map.keySet().iterator();
		while(it.hasNext())
		{
			Socket usersk=(Socket)map.get(it.next());
			OutputStream uos = usersk.getOutputStream();
			new PrintStream(uos).println(msg);
		}
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
}