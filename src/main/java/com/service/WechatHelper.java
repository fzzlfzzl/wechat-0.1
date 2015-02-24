package com.service;

import com.test.tools.JsonObject;
import com.util.HttpClient;

public class WechatHelper {
	private static String APP_SECRET = "a22d71808ae9b3c7f9d926c7967a2c0c";
	private static String APP_ID = "wxddd3064d4314479d";

	private static String OWN_ID = "gh_d46ef0e2c7e5";

	private static String ACCESS_TOKEN = "5nLhQDU8LgjjLPSV0i18JYpVbHuNUtzkjGLnvKZPVWKXIL-kfL4WXnMFXyUu0w-_v1S4eKCIuyYDPZhImcTQEuUgtyhxKNvwGDr12fNXclQ";

	public static String getOwnId() {
		return OWN_ID;
	}

	public static String getAccessTokenUrl() {
		String urlFmt = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=%s&secret=%s";
		String url = String.format(urlFmt, APP_ID, APP_SECRET);
		return url;
	}

	public static String getRegistMenuUrl() {
		String urlFmt = "https://api.weixin.qq.com/cgi-bin/menu/create?access_token=%s";
		String url = String.format(urlFmt, ACCESS_TOKEN);
		return url;
	}

	public static String getAccessToken() {
		return ACCESS_TOKEN;
	}

	public static String getUserInfo(String openId) throws Exception {
		String urlFmt = "https://api.weixin.qq.com/cgi-bin/user/info?access_token=%s&openid=%s&lang=zh_CN";
		String url = String.format(urlFmt, ACCESS_TOKEN, openId);
		HttpClient client = new HttpClient(url);
		String res = client.get();
		JsonObject obj = JsonObject.toJsonObject(res);
		return obj.get("nickname").toString();
	}

}
