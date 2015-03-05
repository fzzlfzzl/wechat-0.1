package com.service.wechat;

public interface Const {

	// 消息类型
	public static class Type {
		public final static String TEXT = "text";
		public final static String EVENT = "event";
		public final static String CLICK = "CLICK";
	}

	// 事件的key
	public static class EventKey {
		public final static String ADDRESS = "ADDRESS";
		public static final String ORDER = "ORDER";
	}

	// 菜单名称
	public static class MenuName {
		public static final String ADDRESS = "地址";
		public static final String ORDER = "订餐";
	}

	// 响应
	public static class MsgReply {
		public static final String NORMAL = "你好";
		public final static String ADDR = "请输入地址";
		public static final String ADDR_CHK = "您的地址是'%s', 是否进行修改, 回复'是'后根据提示进行修改";
		public static final String ADDR_UPDT_SUCC = "地址保存成功";
	}

	// 请求
	public static class Msg {
		public static final String YES = "是";
	}

	// 身份认证
	public static class Auth {
		public static final String LOGIN = "LOGIN";
		public static final String SA_LOGIN = "SA_LOGIN";
	}

}
