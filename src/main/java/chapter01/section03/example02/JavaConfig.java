package chapter01.section03.example02;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JavaConfig {

  @Bean
  public FunctionService functionService() {
    return new FunctionService();
  }

  @Bean
  public UseFunctionService userFunctionService() {
    UseFunctionService service = new UseFunctionService();
    service.setFunctionService(functionService());
    return service;
  }
}
