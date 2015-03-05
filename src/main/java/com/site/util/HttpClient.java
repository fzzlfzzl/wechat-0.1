package com.site.util;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class HttpClient {

	private String url;

	public HttpClient(String url) {
		this.url = url;
	}

	public String get() {
		try {
			URL url = new URL(this.url);
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.connect();
			BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			StringBuffer buffer = new StringBuffer();
			String line;

			while ((line = reader.readLine()) != null) {
				buffer.append(line);
			}
			reader.close();
			connection.disconnect();
			return buffer.toString();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public String post(String req) {
		try {
			URL postUrl = new URL(url);
			HttpURLConnection connection = (HttpURLConnection) postUrl.openConnection();
			connection.setDoOutput(true);
			connection.setDoInput(true);

			connection.setRequestMethod("POST");

			connection.setUseCaches(false);
			connection.setInstanceFollowRedirects(true);
			connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
			connection.getOutputStream().write(req.getBytes());

			StringBuffer buffer = new StringBuffer();
			BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			String line;

			while ((line = reader.readLine()) != null) {
				buffer.append(line);
			}
			reader.close();
			connection.disconnect();
			return buffer.toString();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

}
