package chapter02.section03.example01;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Example020301 {
  public static void main(String[] args) {
    AnnotationConfigApplicationContext context =
        new AnnotationConfigApplicationContext(PrePostConfig.class);
    BeanWayService beanWayService = context.getBean(BeanWayService.class);
    JSR250WayService jsr250WayService = context.getBean(JSR250WayService.class);
    context.close();
  }
}
