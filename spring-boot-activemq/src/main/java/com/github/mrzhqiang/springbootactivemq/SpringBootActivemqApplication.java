package com.github.mrzhqiang.springbootactivemq;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jms.core.JmsTemplate;

@SpringBootApplication
public class SpringBootActivemqApplication implements CommandLineRunner {

  public static void main(String[] args) {
    SpringApplication.run(SpringBootActivemqApplication.class, args);
  }

  @Autowired JmsTemplate jmsTemplate;

  @Override public void run(String... args) throws Exception {
    jmsTemplate.send("my-destination", new Msg());
  }
}
