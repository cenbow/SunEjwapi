package com.huaao.sunejwapi.api.test;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class SocketTestServer extends Thread{
	private static ServerSocket serverSocket;
	static{
		try {
			serverSocket = new ServerSocket(8089);
		} catch (IOException e) {
			e.printStackTrace();
		}		
	}
	
	@Override
	public void run() {
		try {
			Socket server = serverSocket.accept();
			DataInputStream in = new DataInputStream(server.getInputStream());
			System.out.println(in.readUTF());
			DataOutputStream out = new DataOutputStream(server.getOutputStream());
			out.writeUTF("I'm Server!");
			server.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		Thread t = new SocketTestServer();
		t.start();
	}
		
}
