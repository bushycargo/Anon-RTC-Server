package com.josephaines.anonrtc.message;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MessageService {

    private final MessageRepository messageRepository;

    @Autowired
    public MessageService(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    public List<Message> getMessages(){
        return messageRepository.findAll();
    }

    public void addNewMessage(Message message) {
        messageRepository.save(message);
    }

    public void deleteMessage(Long id) {
        boolean messageExists = messageRepository.existsById(id);
        if (!messageExists){
            throw new IllegalStateException("Message does not exist");
        }
        messageRepository.deleteById(id);
    }
}
