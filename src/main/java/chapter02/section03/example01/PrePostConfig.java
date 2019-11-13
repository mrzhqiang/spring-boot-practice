package chapter02.section03.example01;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan("chapter02.section03.example01")
public class PrePostConfig {
  @Bean(initMethod = "init", destroyMethod = "destroy")
  BeanWayService beanWayService() {
    return new BeanWayService();
  }

  @Bean
  JSR250WayService jsr250WayService() {
    return new JSR250WayService();
  }
}
