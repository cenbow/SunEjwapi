package com.huaao.sunejwapi.common.web;

import java.io.CharArrayWriter;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;

public class ResponseWrapper extends HttpServletResponseWrapper {
	private PrintWriter cachedWriter;
	private CharArrayWriter bufferedWriter;

	public ResponseWrapper(HttpServletResponse response) {
		super(response);
		bufferedWriter = new CharArrayWriter();
		cachedWriter = new PrintWriter(bufferedWriter);
	}

	@Override
	public PrintWriter getWriter() {
		return cachedWriter;
	}

	/**
	 * 获取原始的HTML页面内容。
	 * 
	 * @return
	 */
	public String getResult() {
		return bufferedWriter.toString();
	}
}
