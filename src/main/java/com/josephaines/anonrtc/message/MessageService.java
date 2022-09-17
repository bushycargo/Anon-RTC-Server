package com.josephaines.anonrtc.message;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;

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

    @Transactional
    public void updateMessage(Long id, Long sessionId, String content) {
        Message message = messageRepository.findById(id).orElseThrow(() -> new IllegalStateException("Message does not exist"));
        if (sessionId != null && !Objects.equals(message.getSessionId(), sessionId)){
            message.setSessionId(sessionId);
        }
        if (content != null && content.length() > 0 && !Objects.equals(message.getContent(), content)){
            message.setContent(content);
        }
    }

    public List<Message> getMessagesBySessionID(long id) {
        List<Message> messages = messageRepository.findAll();
        for (Message message :
                messages) {
            if (!(message.getSessionId() == id)){
                messages.remove(message);
            }
        }
        return messages;
    }
}
