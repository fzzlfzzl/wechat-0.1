package com.dao.bean;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

import javax.persistence.Entity;

@Entity
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id = 0;

	@Column(nullable = false, unique = true)
	private String openId = null;

	private String phone = null;

	@OneToMany
	@JoinColumn(name = "aid")
	private List<Address> addressList = new ArrayList<Address>();

	@OneToMany
	@JoinColumn(name = "mid")
	private List<MessageHistory> messageHistory = new ArrayList<MessageHistory>();

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getOpenId() {
		return openId;
	}

	public void setOpenId(String openId) {
		this.openId = openId;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public List<Address> getAddressList() {
		return addressList;
	}

	public void setAddressList(List<Address> addressList) {
		this.addressList = addressList;
	}

	public List<MessageHistory> getMessageHistory() {
		return messageHistory;
	}

	public void setMessageHistory(List<MessageHistory> messageHistory) {
		this.messageHistory = messageHistory;
	}

}
