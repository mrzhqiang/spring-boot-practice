package chapter03.section01.example01;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Example030101 {
  public static void main(String[] args) {
    AnnotationConfigApplicationContext context =
        new AnnotationConfigApplicationContext(AwareConfig.class);
    AwareService awareService = context.getBean(AwareService.class);
    awareService.outputResult();
    context.close();
  }
}
