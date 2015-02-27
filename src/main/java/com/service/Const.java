package com.service;

public interface Const {

	// 消息类型
	public final static String TYPE_TEXT = "text";
	public final static String TYPE_EVENT = "event";
	public final static String TYPE_CLICK = "CLICK";

	// 事件的key
	public final static String EVENT_KEY_ADDRESS = "ADDRESS";

	// 响应
	public static final String RPY_NORMAL = "你好";
	public final static String RPY_ADDR = "请输入地址";
	public static final String RPY_ADDR_CHK = "您的地址是'%s', 是否进行修改, 回复'是'后根据提示进行修改";
	public static final String RPY_ADDR_UPDT_SUCC = "地址保存成功";

	// 请求
	public static final String REQ_YES = "是";

	// session
	public static final String SES_LOGIN = "LOGIN";

}
