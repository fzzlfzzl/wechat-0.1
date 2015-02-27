package com.test.util;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.Socket;

import org.apache.log4j.Logger;

public class HttpTestClient {

	private static Logger logger = Logger.getLogger(HttpTestClient.class);

	private String host;
	private int port;
	private String query;
	private String userAgent;

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

	public String getQuery() {
		return query;
	}

	public void setQuery(String query) {
		this.query = query;
	}

	public String getUserAgent() {
		return userAgent;
	}

	public void setUserAgent(String userAgent) {
		this.userAgent = userAgent;
	}

	private String getHeader() {
		/*
		 * GET /rss HTTP/1.0 User-Agent: Wget/1.11.4 Red Hat modified Accept:
		 * Host: 127.0.0.1:7080 Connection: Keep-Alive
		 */
		StringBuffer sb = new StringBuffer();
		String line = null;
		if (!query.startsWith("/")) {
			query = "/" + query;
		}
		line = String.format("GET %s HTTP/1.0\n", query);
		sb.append(line);
		line = String.format("User-Agent: wget\n");
		sb.append(line);
		line = "Accept: */*\n";
		sb.append(line);
		line = String.format("Host: %s:%d\n", host, port);
		sb.append(line);
		line = "Connection: Keep-Alive\n";
		sb.append(line);
		return sb.toString();
	}

	private String getTail() {
		return "\r\n";
	}

	public String get() throws Exception {
		Socket socket = new Socket(host, port);
		String req = getHeader() + getTail();
		logger.error("\n" + req);
		socket.getOutputStream().write(req.getBytes());
		StringBuffer sb = new StringBuffer();
		BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		String line = null;
		while ((line = br.readLine()) != null) {
			sb.append(line);
			sb.append("\n");
			logger.error(line);
		}
		try {
			socket.getOutputStream().close();
		} catch (Exception e) {
		}
		try {
			socket.getInputStream().close();
		} catch (Exception e) {
		}

		return sb.toString();
	}

	public static void main(String[] args) throws Exception {
		// ServerSocket server = new ServerSocket(6666);
		// Socket socket = server.accept();
		// while (true) {
		// int read = socket.getInputStream().read();
		// if (read < 0) {
		// break;
		// }
		// byte b = (byte) read;
		// byte[] bytes = new byte[] { b };
		// String str = new String(bytes);
		// String output = String.format("%d[%s]", read, str);
		// System.out.print(output);
		// }
		// System.exit(0);

		HttpTestClient client = new HttpTestClient();
		client.setHost("127.0.0.1");
		client.setPort(8080);
		client.setQuery("/wechat/site/index");
		client.get();
	}
}
