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

public class MyTest2 {

	public static void main(String[] args) throws Exception {
		Map<Character, Integer> excelTitle = new HashMap<>();
		Character ch = 'A';
		Integer in = 0;
		while (ch <= 'U')
			excelTitle.put(ch++, in++);
		String filepath = "f://高考派-专业大全.xlsx";
		ReadExcelUtils excelReader = new ReadExcelUtils(filepath);
		Map<Integer, Map<Integer, Object>> map = excelReader.readExcelContent();
		Iterator<Integer> iterator = map.keySet().iterator();
		String result = "";
		while (iterator.hasNext()) {
			int i = iterator.next();
			Map<Integer, Object> m = map.get(i);
			String pr_name = String.valueOf(m.get(excelTitle.get('A'))).replace("\n", "");
			if(StringUtils.isEmpty(pr_name))
				break;
			String pr_id = String.valueOf(m.get(excelTitle.get('B'))).replace("\n", "");
			String state = String.valueOf(m.get(excelTitle.get('D'))).replace("\n", "");
			String pr_info = String.valueOf(m.get(excelTitle.get('C'))).replace("\n", "").replace("\t", "");
			String pr_course = String.valueOf(m.get(excelTitle.get('E'))).replace("\n", "");
			ProfessionItem pItem = new ProfessionItem(pr_name, pr_id, state, pr_info, pr_course);
			System.out.println(JSONUtil.jsonEncode(pItem));
			result = result + JSONUtil.jsonEncode(pItem);
			result = result + "\r\n";
			result = result + "\r\n";
		}
		File f = new File("f://professionInfo.txt");      
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
