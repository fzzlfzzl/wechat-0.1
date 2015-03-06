package com.site.util;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.KeyStore;
import java.security.SecureRandom;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManagerFactory;

public class HttpsClient {

	private String url = null;

	private String sslKeyStorePath = null;
	private String sslKeyStorePassword = null;

	public HttpsClient(String url) {
		this.url = url;
	}

	public void setJKS(String path, String password) {
		sslKeyStorePath = path;
		sslKeyStorePassword = password;
	}

	public String get() {
		try {
			String bufferString = null;
			StringBuffer buffer = new StringBuffer();

			URL url = new URL(this.url);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			conn.setDoOutput(true);
			conn.setDoInput(true);
			conn.setUseCaches(false); // 不允许使用缓存
			conn.setRequestProperty("connection", "keep-alive");
			System.setProperty("http.keepAlive", "false");
			conn.setRequestProperty("content-type", "text/xml");
			conn.setRequestProperty("Charset", "UTF-8");
			InputStream is = conn.getInputStream();
			BufferedReader br = new BufferedReader(new InputStreamReader(is));
			while ((bufferString = br.readLine()) != null) {
				buffer.append(bufferString);
			}
			is.close();
			conn.disconnect();
			String resString = buffer.toString();
			return resString;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public String post(String reqString) throws Exception {
		String bufferString = null;
		StringBuffer buffer = new StringBuffer();
		// setJKS();
		URL url = new URL(this.url);
		HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
		setSSLSocketFactory(conn);
		// conn.setSSLSocketFactory(factory);
		conn.setRequestMethod("POST");
		conn.setDoOutput(true);
		conn.setDoInput(true);
		conn.setHostnameVerifier(new HostnameDoNothingVerifier());
		conn.setUseCaches(false); // 不允许使用缓存
		conn.setRequestProperty("connection", "keep-alive");
		System.setProperty("http.keepAlive", "false");
		conn.setRequestProperty("content-type", "text/xml");
		conn.setRequestProperty("Charset", "UTF-8");
		OutputStream os = conn.getOutputStream();
		os.write(reqString.getBytes("UTF-8"));
		os.close();
		InputStream is = conn.getInputStream();
		BufferedReader br = new BufferedReader(new InputStreamReader(is));
		while ((bufferString = br.readLine()) != null) {
			buffer.append(bufferString);
		}
		is.close();
		String resString = buffer.toString();
		return resString;
	}

	private void setSSLSocketFactory(HttpsURLConnection conn) throws Exception {
		if (null == sslKeyStorePath || null == sslKeyStorePassword) {
			return;
		}

		String type = "TLS";// 类型
		SSLContext ctx = SSLContext.getInstance(type);
		KeyManagerFactory kmf = KeyManagerFactory.getInstance("SunX509");
		TrustManagerFactory tmf = TrustManagerFactory.getInstance("SunX509");
		KeyStore ks = KeyStore.getInstance("JKS");
		KeyStore tks = KeyStore.getInstance("JKS"); // 载入keystore
		ks.load(new FileInputStream(sslKeyStorePath), sslKeyStorePassword.toCharArray());
		tks.load(new FileInputStream(sslKeyStorePath), sslKeyStorePassword.toCharArray());
		kmf.init(ks, sslKeyStorePassword.toCharArray());
		tmf.init(tks);
		ctx.init(kmf.getKeyManagers(), tmf.getTrustManagers(), new SecureRandom());
		SSLSocketFactory factory = ctx.getSocketFactory();
		conn.setSSLSocketFactory(factory);
		// ss = (SSLServerSocket)
		// ctx.getServerSocketFactory().createServerSocket(port);
		// ss.setNeedClientAuth(true);// 客户端要认证
	}

	public JsonObject post(JsonObject obj) throws Exception {
		String reqJson = obj.toJsonString();
		String resJson = post(reqJson);
		return JsonObject.toJsonObject(resJson);
	}

	public XmlObject post(XmlObject obj) throws Exception {
		String reqXml = obj.toXmlString();
		String resXml = post(reqXml);
		return XmlObject.toXmlObject(resXml);
	}

}

class HostnameDoNothingVerifier implements HostnameVerifier {

	public boolean verify(String urlHostName, SSLSession session) {
		return true;
	}

}
