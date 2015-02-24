package com.service;

import org.apache.log4j.Logger;

public class LoggerHelper {

	private static Logger logger = Logger.getLogger(LoggerHelper.class);

	public static Logger logger() {
		return logger;
	}
}
