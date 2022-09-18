package com.josephaines.anonrtc.message;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.*;
import java.util.List;

@RestController
@RequestMapping(path ="api/v1/message")
public class MessageController {

    private final MessageService messageService;

    @Autowired
    public MessageController(MessageService messageService) throws FileNotFoundException {
        this.messageService = messageService;
    }

    @GetMapping("/connect")
    public Long connect(){

        JSONParser jsonParser = new JSONParser();

        try (Reader reader = new FileReader("data.json")){
            JSONObject jsonObject = (JSONObject) jsonParser.parse(reader);
            System.out.println(jsonObject);
            Long sessionAmount = (Long) jsonObject.get("sessionAmount");
            Boolean newId = (Boolean) jsonObject.get("newId");
            if (!newId){
                JSONObject data = new JSONObject();
                data.put("sessionAmount", sessionAmount);
                data.put("newId", true);
                FileWriter file = new FileWriter("data.json");
                file.write(data.toJSONString());
                file.close();
                return sessionAmount;
            }
            sessionAmount++;
            JSONObject data = new JSONObject();
            data.put("sessionAmount", sessionAmount);
            data.put("newId", false);
            FileWriter file = new FileWriter("data.json");
            file.write(data.toJSONString());
            file.close();
            return sessionAmount;

        } catch (IOException | ParseException e) {
            throw new RuntimeException(e);
        }
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
