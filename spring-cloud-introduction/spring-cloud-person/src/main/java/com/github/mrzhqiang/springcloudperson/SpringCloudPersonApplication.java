package com.github.mrzhqiang.springcloudperson;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class SpringCloudPersonApplication {
  public static void main(String[] args) {
    SpringApplication.run(SpringCloudPersonApplication.class, args);
  }
}
