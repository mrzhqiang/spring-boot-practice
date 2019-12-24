package com.github.mrzhqiang.springbootactivemq;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
public class Receiver {
  @JmsListener(destination = "my-destination")
  public void receiverMessage(String message) {
    System.out.println("接收到： <" + message + ">");
  }
}
