package com.service.bean;

import com.service.Const;

public interface IUserBean {

	public String getOpenId();

	public void setOpenId(String openId);

	public String getTelephone();

	public void setTelephone(String telephone);

	public String getAddress();

	public void setAddress(String address);

	public Const getLastMessage();

	public void setLastMessage(Const lastMessage);
}
