package com.huaao.sunejwapi.api.test;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class SocketTest {
	public static void main(String[] args) throws Exception {
		Socket client = new Socket("localhost", 8089);
		OutputStream outToServer = client.getOutputStream();
		DataOutputStream out =  new DataOutputStream(outToServer);
		out.writeUTF("hello !!");
		
		InputStream inFromServer = client.getInputStream();
		DataInputStream in = new DataInputStream(inFromServer);
		System.out.println(in.readUTF());
		
		client.close();
	}
}
