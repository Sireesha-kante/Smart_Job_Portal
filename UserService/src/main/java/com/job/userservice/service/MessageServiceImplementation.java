/*
 * package com.job.userservice.service;
 * 
 * import java.time.LocalDateTime; import java.util.List;
 * 
 * import org.springframework.beans.factory.annotation.Autowired; import
 * org.springframework.stereotype.Service;
 * 
 * import com.job.userservice.entity.Message; import
 * com.job.userservice.entity.User; import
 * com.job.userservice.repository.MessageRepository;
 * 
 * @Service public class MessageServiceImplementation implements MessageService
 * {
 * 
 * @Autowired private MessageRepository messageRepository;
 * 
 * @Override public Message sendMessage(User sender, User receiver, String
 * content, LocalDateTime sentAt) { Message message = new Message(sender,
 * receiver, content, sentAt.now()); return messageRepository.save(message); }
 * 
 * @Override public List<Message> getMessages(User user) { // TODO
 * Auto-generated method stub return
 * messageRepository.findBySenderIdOrReceiverId(user.getId(), user.getId());
 * 
 * }
 * 
 * }
 */