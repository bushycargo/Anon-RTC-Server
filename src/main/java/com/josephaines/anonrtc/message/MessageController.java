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

    @GetMapping("/by/sid")
    public List<Message> getMessageBySessionID(@RequestParam("id") Long sessionId) {
        return messageService.getMessagesBySessionID(sessionId );
    }

    @PostMapping
    public void registerMessage(@RequestBody Message message){
        messageService.addNewMessage(message);
    }

    @DeleteMapping(path = "{id}")
    public void deleteMessage(@PathVariable("id") Long id){
        messageService.deleteMessage(id);
    }

    @PutMapping(path = "{id}")
    public void updateMessage(
            @PathVariable("id") Long id,
            @RequestParam(required = false) Long sessionId,
            @RequestParam(required = false) String content){
        messageService.updateMessage(id, sessionId, content);
    }

}
