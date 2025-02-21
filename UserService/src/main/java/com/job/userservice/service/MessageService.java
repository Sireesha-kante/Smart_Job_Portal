package com.job.userservice.service;

import java.time.LocalDateTime;
import java.util.List;

import com.job.userservice.entity.Message;
import com.job.userservice.entity.User;

public interface MessageService {

	public Message sendMessage( User sender, User receiver, String content, LocalDateTime sentAt);
    public List<Message> getMessages(User user);

}
