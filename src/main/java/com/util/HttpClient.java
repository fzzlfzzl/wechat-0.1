package com.util;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class HttpClient {

	public static final String GET_URL = " http://localhost:8080/demo/  ";

	public static final String POST_URL = " http://localhost:8080/demo/  ";

	private String url;

	public HttpClient(String url) {
		this.url = url;
	}

	public String get() throws Exception {
		URL getUrl = new URL(url);
		HttpURLConnection connection = (HttpURLConnection) getUrl
				.openConnection();
		connection.connect();
		BufferedReader reader = new BufferedReader(new InputStreamReader(
				connection.getInputStream()));
		StringBuffer buffer = new StringBuffer();
		String line;

		while ((line = reader.readLine()) != null) {
			buffer.append(line);
		}
		reader.close();
		connection.disconnect();
		return buffer.toString();
	}

	public String post(String req) throws Exception {
		URL postUrl = new URL(url);
		HttpURLConnection connection = (HttpURLConnection) postUrl
				.openConnection();
		connection.setDoOutput(true);
		connection.setDoInput(true);
		connection.setRequestMethod("POST");
		connection.setUseCaches(false);
		connection.setInstanceFollowRedirects(true);
		connection.setRequestProperty("Content-Type",
				"application/x-www-form-urlencoded");
		connection.getOutputStream().write(req.getBytes());

		StringBuffer buffer = new StringBuffer();
		BufferedReader reader = new BufferedReader(new InputStreamReader(
				connection.getInputStream()));
		String line;

		while ((line = reader.readLine()) != null) {
			buffer.append(line);
		}
		reader.close();
		return buffer.toString();
	}

}
