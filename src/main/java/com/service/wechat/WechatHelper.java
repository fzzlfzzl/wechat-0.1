package com.service.wechat;

import org.apache.log4j.Logger;
import org.hibernate.Session;

import com.site.util.HttpClient;
import com.site.util.JsonObject;
import com.web.dao.entity.AccessToken;
import com.web.dao.impl.AccessTokenDao;
import com.web.interceptor.context.UserContext;

public class WechatHelper {

	private static Logger logger = Logger.getLogger(WechatHelper.class);

	private static String APP_SECRET = "a22d71808ae9b3c7f9d926c7967a2c0c";
	private static String APP_ID = "wxddd3064d4314479d";

	private static String getAccessToken() {
		Session session = UserContext.current().getSession();
		AccessTokenDao dao = new AccessTokenDao(session);
		AccessToken token = dao.get();
		if (token == null) {
			token = new AccessToken();
		}
		if (isTimeout(token) || token.getId() == 0) {
			AccessToken tmp = queryAccessToken();
			token.setAccessToken(tmp.getAccessToken());
			token.setTimeout(tmp.getTimeout());
			dao.saveOrUpdate(token);
		}
		return token.getAccessToken();
	}

	private static boolean isTimeout(AccessToken token) {
		if (token.getUpdatetime() + token.getTimeout() * 1000 - 200 < System.currentTimeMillis()) {
			return true;
		}
		return false;
	}

	private static AccessToken queryAccessToken() {
		String url = getAccessTokenUrl();
		HttpClient client = new HttpClient(url);
		String res = client.get();
		logger.info("Access Token: " + res);
		JsonObject obj = JsonObject.toJsonObject(res);
		AccessToken ret = new AccessToken();
		if (!obj.get("errcode").isNull() || obj.get("access_token").isNull()) {
			// 出错了
			logger.error(res);
			throw new RuntimeException("Get Access Token Fail");
		} else {
			ret.setAccessToken(obj.get("access_token").toString());
			ret.setTimeout(obj.get("expires_in").toInt());
			return ret;
		}
	}

	private static String getAccessTokenUrl() {
		String urlFmt = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=%s&secret=%s";
		String url = String.format(urlFmt, APP_ID, APP_SECRET);
		return url;
	}

	private static String getRegistMenuUrl() {
		String urlFmt = "https://api.weixin.qq.com/cgi-bin/menu/create?access_token=%s";
		String url = String.format(urlFmt, getAccessToken());
		return url;
	}

	public static void registMenu(String req) {
		String url = getRegistMenuUrl();
		HttpClient client = new HttpClient(url);
		logger.info("Regist Menu Req: " + req);
		String res = client.post(req);
		logger.info("Regist Menu Res: " + res);
		JsonObject obj = JsonObject.toJsonObject(res);
		if (obj.get("errcode").toInt() != 0) {
			logger.error(res);
			throw new RuntimeException("Regist Menu Fail:" + res);
		}
	}

	public static String getUserInfo(String openId) throws Exception {
		String urlFmt = "https://api.weixin.qq.com/cgi-bin/user/info?access_token=%s&openid=%s&lang=zh_CN";
		String url = String.format(urlFmt, getAccessToken(), openId);
		HttpClient client = new HttpClient(url);
		String res = client.get();
		JsonObject obj = JsonObject.toJsonObject(res);
		return obj.get("nickname").toString();
	}

}
