package com.github.mrzhqiang.springbootweb;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class WsController {
  @MessageMapping("/welcome")
  @SendTo("/topic/getResponse")
  public WiselyResponse say(WiselyMessage message) {
    try {
      Thread.sleep(3000);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    return new WiselyResponse("Welcome, " + message.getName() + "!");
  }
}
