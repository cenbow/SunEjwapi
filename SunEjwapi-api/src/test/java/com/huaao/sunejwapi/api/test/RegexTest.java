package com.huaao.sunejwapi.api.test;

public class RegexTest {
	/***
	. 任何字符
	* 0-n
	?   	0-1;
	+ 		1-n
	{n}		n;
	{n,m}   n,m
	{n,}    n-...
	*/
	public static void main(String[] args) {
		String regex = "[-+]?0[xX]{1}[0-9a-fA-F]{2}";
		System.out.println("-0X0e".matches(regex));
		String regex2 = "\\s+\\d+";
		System.out.println("   1".matches(regex2));
		String regex3 = "^h.*";
		System.out.println("h  tt".matches(regex3));
		String regex4 = "cat|dog";
		System.out.println("dog".matches(regex4));
		String regex5 = "\\d+@\\w+\\.com";
		System.out.println("31@163.com".matches(regex5));
		String regex6 = "((1\\d)|20)\\d{2}\\.(0?[1-9]|(1[0-2]{1}))\\."+
						"([1-9]|([0-2]\\d)|3[0-1]{1})";
		System.out.println("2011.01.01".matches(regex6));
		String regex7 = ".*r$";//匹配末尾字符
		System.out.println("  ssr".matches(regex7));
		String regex8 = "^h.*d$";
		System.out.println("hello  world".matches(regex8));
		
		String regex9 = "^([a-z]|\\.)+$";
		System.out.println("image.baidu.com".matches(regex9));
		
	}
}
