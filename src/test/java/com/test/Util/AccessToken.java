package com.test.Util;

import com.service.WechatService;
import com.util.HttpClient;

public class AccessToken {

	public static void main(String[] args) throws Exception {
		String url = WechatService.getAccessTokenUrl();
		HttpClient client = new HttpClient(url);
		String ret = client.get();
		System.out.println(ret);
	}
}
