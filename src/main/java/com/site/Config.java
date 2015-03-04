package com.site;

import com.site.util.Util;

public class Config {

	public static String getHost() {
		if (Util.isDevelopEnvironment()) {
			return "http://127.0.0.1:8080/";
		} else {
			return "http://jiuwubaodu.duapp.com/";
		}
	}
}
