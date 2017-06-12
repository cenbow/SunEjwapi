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

public class MyTest {

	public static void main(String[] args) throws Exception {
		Map<Character, Integer> excelTitle = new HashMap<>();
		Character ch = 'A';
		Integer in = 0;
		while (ch <= 'U')
			excelTitle.put(ch++, in++);
		String filepath = "f://大学 - 副本.xlsx";
		ReadExcelUtils excelReader = new ReadExcelUtils(filepath);
		Map<Integer, Map<Integer, Object>> map = excelReader.readExcelContent();
		Iterator<Integer> iterator = map.keySet().iterator();
		String result = "";
		while (iterator.hasNext()) {
			int i = iterator.next();
			Map<Integer, Object> m = map.get(i);
			String college_name = String.valueOf(m.get(excelTitle.get('A'))).replace("\n", "");
			if(StringUtils.isEmpty(college_name))
				break;
			String college_enname = String.valueOf(m.get(excelTitle.get('B'))).replace("\n", "");
			String college_type = String.valueOf(m.get(excelTitle.get('G'))).replace("\n", "");
			String college_area = String.valueOf(m.get(excelTitle.get('P'))).replace("\n", "").replace(" ", "");
			String college_info = String.valueOf(m.get(excelTitle.get('C'))).replace("\n", "");
			String createtime = String.valueOf(m.get(excelTitle.get('D'))).replace("\n", "");
			String belong = String.valueOf(m.get(excelTitle.get('F'))).replace("\n", "");
			String student_num = String.valueOf(m.get(excelTitle.get('H'))).replace("\n", "");
			String doctor_num = String.valueOf(m.get(excelTitle.get('I'))).replace("\n", "");
			String academician_number = String.valueOf(m.get(excelTitle.get('J'))).replace("\n", "");
			String master_number = String.valueOf(m.get(excelTitle.get('K'))).replace("\n", "");
			String employment = String.valueOf(m.get(excelTitle.get('L'))).replace("\n", "");
			String own_professional = String.valueOf(m.get(excelTitle.get('M'))).replace("\n", "");
			String point_professional = String.valueOf(m.get(excelTitle.get('E'))).replace("\n", "");
			String nation_professional = String.valueOf(m.get(excelTitle.get('O'))).replace("\n", "");
			String tuition = String.valueOf(m.get(excelTitle.get('N'))).replace("\n", "");
			String college_address = String.valueOf(m.get(excelTitle.get('Q'))).replace("\n", "");
			String college_number = String.valueOf(m.get(excelTitle.get('R'))).replace("\n", "");
			String college_email = String.valueOf(m.get(excelTitle.get('S'))).replace("\n", "");
			String college_website = String.valueOf(m.get(excelTitle.get('T'))).replace("\n", "");
			String college_enrollweb = String.valueOf(m.get(excelTitle.get('U'))).replace("\n", "");
			School school = new School(college_name, college_enname, college_type, college_area, college_info,
					createtime, belong, student_num, doctor_num, academician_number, master_number, employment,
					own_professional, point_professional, nation_professional, tuition, college_address, college_number,
					college_email, college_website, college_enrollweb);
			System.out.println(JSONUtil.jsonEncode(school));
			result = result + JSONUtil.jsonEncode(school);
			result = result + "\r\n";
			result = result + "\r\n";
		}
		File f = new File("f://schoolInfo.txt");      
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
