package com.service;

import com.test.tools.JsonObject;
import com.util.HttpClient;

public class WeChat {

	private static String appSecret = "a22d71808ae9b3c7f9d926c7967a2c0c";
	private static String appId = "wxddd3064d4314479d";

	private static String accessToken = "MGgXQYyh-18ORaFjR69-ZXPcIIeHe2HaIo5-xWplGr6NHlgOplCNv3vdEpxUt8eNi6l0af5YYMViU6zg7hKywLSt1c906YKZN56oSd3g-DM";

	public static String getAccessTokenUrl() {
		String urlFmt = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=%s&secret=%s";
		String url = String.format(urlFmt, appId, appSecret);
		return url;
	}

	public static String getAccessToken() {
		return accessToken;
	}

	public static String getUserInfo(String openId) throws Exception {
		String urlFmt = "https://api.weixin.qq.com/cgi-bin/user/info?access_token=%s&openid=%s&lang=zh_CN";
		String url = String.format(urlFmt, accessToken, openId);
		HttpClient client = new HttpClient(url);
		String res = client.get();
		JsonObject obj = JsonObject.toJsonObject(res);
		return obj.get("nickname").toString();
	}
}
