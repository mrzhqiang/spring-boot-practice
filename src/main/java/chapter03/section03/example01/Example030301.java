package chapter03.section03.example01;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Example030301 {
  public static void main(String[] args) {
    AnnotationConfigApplicationContext context =
        new AnnotationConfigApplicationContext(TaskSchedulerConfig.class);
  }
}
