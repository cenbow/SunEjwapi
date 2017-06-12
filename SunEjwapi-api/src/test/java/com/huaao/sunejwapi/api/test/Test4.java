package com.huaao.sunejwapi.api.test;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.InputStream;
import java.io.InputStreamReader;

public class Test4 {
	public static void main(String[] args) throws Exception {
		//读txt
		File file = new File("f:/test.txt");
		InputStreamReader reader = new InputStreamReader(new FileInputStream(file),"gbk");
		BufferedReader br = new BufferedReader(reader);
		StringBuffer sb = new StringBuffer();
		String line = null;
		while((line = br.readLine())!=null)
		sb.append(line).append("\n");
		System.out.println(sb);
		//写txt
		File file2 = new File("f:/test2.txt");
		if(!file2.exists())
			file2.createNewFile();
		BufferedWriter writer = new BufferedWriter(new FileWriter(file2));
		writer.write("ff\r\n哈哈\r\n333");
		writer.flush();
		writer.close();
		}
}
