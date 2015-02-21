package com.service.message;

public interface IEventMessage extends IMessage {

	public String getEventKey();

	public String getEvent();

}
