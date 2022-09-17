package com.josephaines.anonrtc.message;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path ="api/v1/message")
public class MessageController {

    private final MessageService messageService;

    @Autowired
    public MessageController(MessageService messageService) {
        this.messageService = messageService;
    }

    @GetMapping
    public List<Message> getMessages(){
        return messageService.getMessages();
    }

    @PostMapping
    public void registerMessage(@RequestBody Message message){
        messageService.addNewMessage(message);
    }

    @DeleteMapping(path = "{id}")
    public void deleteMessage(@PathVariable("id") Long id){
        messageService.deleteMessage(id);
    }

}
