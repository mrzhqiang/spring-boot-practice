package chapter03.section05.example01;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Example030501 {
  public static void main(String[] args) {
    AnnotationConfigApplicationContext context =
        new AnnotationConfigApplicationContext(DemoConfig.class);
    DemoService demoService = context.getBean(DemoService.class);
    demoService.outputResult();
    context.close();
  }
}
