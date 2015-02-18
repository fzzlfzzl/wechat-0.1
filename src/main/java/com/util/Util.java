package com.util;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

public class Util {

	public static String readStream(InputStream is) throws Exception {
		String line = null;
		StringBuffer buffer = new StringBuffer();
		BufferedReader reader = new BufferedReader(new InputStreamReader(is));
		while ((line = reader.readLine()) != null) {
			buffer.append(line);
		}
		return buffer.toString();
	}

	public static boolean isNullOrEmpty(String str) {
		return null == str || str.length() == 0;
	}
}
