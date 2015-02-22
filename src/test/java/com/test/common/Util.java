package com.test.common;

import com.service.WechatService;
import com.util.HttpClient;

public class Util {

	public static void main(String[] args) throws Exception {
		String url = WechatService.getAccessTokenUrl();
		HttpClient client = new HttpClient(url);
		String ret = client.get();
		System.out.println(ret);
	}
}
