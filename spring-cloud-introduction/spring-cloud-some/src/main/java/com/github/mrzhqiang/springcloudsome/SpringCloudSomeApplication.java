package com.github.mrzhqiang.springcloudsome;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@EnableDiscoveryClient
@RestController
public class SpringCloudSomeApplication {
  public static void main(String[] args) {
    SpringApplication.run(SpringCloudSomeApplication.class, args);
  }

  @Value("${my.message")
  private String message;

  @RequestMapping("/getsome")
  public String getSome() {
    return message;
  }
}
