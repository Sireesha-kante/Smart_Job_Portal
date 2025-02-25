package com.job.messagingservice.service;

import java.time.LocalDateTime;
import java.util.List;

import com.job.messagingservice.entity.Message;


public interface MessageService {

	public Message sendMessage( User sender, User receiver, String content, LocalDateTime sentAt);
    public List<Message> getMessages(User user);

}
