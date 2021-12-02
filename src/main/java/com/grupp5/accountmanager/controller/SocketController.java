package com.grupp5.accountmanager.controller;


import com.grupp5.accountmanager.models.MessageBean;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;


@Controller
public class SocketController {
    Logger logger = Logger.getLogger(SocketController.class.getName());
    @MessageMapping("/ws-chat")
    @SendTo("/topic/user")
    public MessageBean sendToAll(MessageBean message) throws Exception{

        try{
        logger.log(Level.WARNING, message.getMessage());
        } catch (Exception e){
            e.printStackTrace();
        }
        return message;
    }

}
