package com.test.util;

import com.site.util.HttpClient;
import com.wechat.WechatHelper;

public class AccessToken {

	public static void main(String[] args) throws Exception {
		String url = WechatHelper.getAccessTokenUrl();
		HttpClient client = new HttpClient(url);
		String ret = client.get();
		System.out.println(ret);
	}
}
