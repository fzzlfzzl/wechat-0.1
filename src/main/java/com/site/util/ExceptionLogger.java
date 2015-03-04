package com.site.util;

import java.io.PrintWriter;
import java.io.StringWriter;

public class ExceptionLogger {

	private Exception ex;

	public ExceptionLogger(Exception e) {
		this.ex = e;
	}

	@Override
	public String toString() {
		StringWriter stringWriter = new StringWriter();
		PrintWriter writer = new PrintWriter(stringWriter);
		ex.printStackTrace(writer);
		StringBuffer buffer = stringWriter.getBuffer();
		return buffer.toString();
	}

}
