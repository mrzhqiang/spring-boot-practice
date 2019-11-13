package chapter01.section03.example03;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Example03 {
  public static void main(String[] args) {
    AnnotationConfigApplicationContext context =
        new AnnotationConfigApplicationContext(AopConfig.class);
    DemoAnnotationService annotationService = context.getBean(DemoAnnotationService.class);
    DemoMethodService methodService = context.getBean(DemoMethodService.class);
    annotationService.add();
    methodService.add();
    context.close();
  }
}
