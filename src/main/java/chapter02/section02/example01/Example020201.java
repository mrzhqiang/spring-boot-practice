package chapter02.section02.example01;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Example020201 {
  public static void main(String[] args) {
    AnnotationConfigApplicationContext context =
        new AnnotationConfigApplicationContext(ElConfig.class);
    ElConfig elConfig = context.getBean(ElConfig.class);
    elConfig.outputResource();
    context.close();
  }
}
