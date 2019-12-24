package com.github.mrzhqiang.springbootrabbitmq;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SpringBootRabbitmqApplication implements CommandLineRunner {

  public static void main(String[] args) {
    SpringApplication.run(SpringBootRabbitmqApplication.class, args);
  }

  @Autowired RabbitTemplate rabbitTemplate;

  @Bean
  public Queue wiselyQueue() {
    return new Queue("my-queue");
  }

  @Override public void run(String... args) throws Exception {
    rabbitTemplate.convertAndSend("my-queue", "来自 Rabbit MQ 的问候");
  }
}
