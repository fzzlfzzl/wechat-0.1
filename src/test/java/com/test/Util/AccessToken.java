package com.test.util;

import com.service.WechatHelper;
import com.util.HttpClient;

public class AccessToken {

	public static void main(String[] args) throws Exception {
		String url = WechatHelper.getAccessTokenUrl();
		HttpClient client = new HttpClient(url);
		String ret = client.get();
		System.out.println(ret);
	}
}
