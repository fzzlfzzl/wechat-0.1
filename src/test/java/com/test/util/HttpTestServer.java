package com.test.util;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class HttpTestServer {

	private ServerSocket serverSocket = null;

	public HttpTestServer(int port) throws Exception {
		serverSocket = new ServerSocket(port);
	}

	public void start() throws Exception {
		System.out.println("Start");
		Socket socket = serverSocket.accept();
		System.out.println("Accept");
		BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		String line = null;
		while ((line = br.readLine()) != null) {
			System.out.println(line);
		}
		socket.close();
		serverSocket.close();
		System.out.println("Finish");
	}

	public static void main(String[] args) throws Exception {
		HttpTestServer server = new HttpTestServer(9090);
		server.start();
	}
}
