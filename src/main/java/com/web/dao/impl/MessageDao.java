package com.web.dao.impl;

import java.util.List;

import org.hibernate.Session;

import com.web.dao.entity.Message;

public class MessageDao extends Dao {

	public MessageDao(Session session) {
		super(session);
	}

	public void save(List<Message> messages) {
		try {
			for (Message message : messages) {

			}
		} catch (Exception e) {

		}
	}

}
