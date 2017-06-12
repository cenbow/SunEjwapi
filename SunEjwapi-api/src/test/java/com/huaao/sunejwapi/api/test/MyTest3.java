package com.huaao.sunejwapi.api.test;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.huaao.sunejwapi.common.util.JSONUtil;
import com.huaao.sunejwapi.common.util.JsonFormatter;
import com.huaao.sunejwapi.common.util.ReadExcelUtils;

public class MyTest3 {

	public static void main(String[] args) throws Exception {
		Map<Character, Integer> excelTitle = new HashMap<>();
		Character ch = 'A';
		Integer in = 0;
		while (ch <= 'U')
			excelTitle.put(ch++, in++);
		String filepath = "f://地区批次线.xlsx";
		ReadExcelUtils excelReader = new ReadExcelUtils(filepath);
		Map<Integer, Map<Integer, Object>> map = excelReader.readExcelContent();
		Iterator<Integer> iterator = map.keySet().iterator();
		String result = "";
		while (iterator.hasNext()) {
			int i = iterator.next();
			Map<Integer, Object> m = map.get(i);
			String line_time = String.valueOf(m.get(excelTitle.get('A'))).replace("\n", "");
			if(StringUtils.isEmpty(line_time))
				break;
		    String line_area = String.valueOf(m.get(excelTitle.get('B'))).replace("\n", "");
		    String line_type = String.valueOf(m.get(excelTitle.get('C'))).replace("\n", "");
		    String line_batch = String.valueOf(m.get(excelTitle.get('D'))).replace("\n", "");
		    String lowest_grade = String.valueOf(m.get(excelTitle.get('E'))).replace("\n", "");
		    ScoreItem sItem = new ScoreItem(line_time, line_area, line_type, line_batch, lowest_grade);
			System.out.println(JSONUtil.jsonEncode(sItem));
			result = result + JSONUtil.jsonEncode(sItem);
			result = result + "\r\n";
			result = result + "\r\n";
		}
		File f = new File("f://ScoreInfo.txt");      
        if (!f.exists())   
        {       
            f.createNewFile();      
        }      
        OutputStreamWriter write = new OutputStreamWriter(new FileOutputStream(f),"gbk");      
        BufferedWriter writer=new BufferedWriter(write);          
        writer.write(result);      
        writer.close();     
		
	}

}
