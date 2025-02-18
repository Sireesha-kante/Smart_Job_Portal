package com.job.userservice.service;

import java.util.List;

import com.job.userservice.entity.Message;

public interface MessageService {

	public Message sendMessage(Long senderId, Long receiverId, String content);
    public List<Message> getMessages(Long userId);

}
