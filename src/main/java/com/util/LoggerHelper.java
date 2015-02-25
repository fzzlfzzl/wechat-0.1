package com.util;

import java.io.PrintWriter;
import java.io.StringWriter;

import org.apache.log4j.Logger;

public class LoggerHelper {

	private static Logger logger = Logger.getLogger(LoggerHelper.class);

	public static void debug(Object obj) {
		logger.debug(obj);
	}

	public static void info(Object obj) {
		logger.info(obj);
	}

	public static void error(Object obj) {
		logger.error(obj);
	}

	public static void exception(Exception e) {
		StringWriter stringWriter = new StringWriter();
		PrintWriter writer = new PrintWriter(stringWriter);
		e.printStackTrace(writer);
		StringBuffer buffer = stringWriter.getBuffer();
		logger.error(buffer.toString());
	}
}
