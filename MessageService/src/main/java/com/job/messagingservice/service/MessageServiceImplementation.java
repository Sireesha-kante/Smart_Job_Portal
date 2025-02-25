package com.job.messagingservice.service;

import java.time.LocalDateTime;
import java.util.List;

import com.job.messagingservice.entity.Message;

public class MessageServiceImplementation implements MessageService {

	@Override
	public Message sendMessage(User sender, User receiver, String content, LocalDateTime sentAt) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Message> getMessages(User user) {
		// TODO Auto-generated method stub
		return null;
	}

}
