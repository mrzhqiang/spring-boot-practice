package chapter02.section05.example01;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Example020501 {
  public static void main(String[] args) {
    AnnotationConfigApplicationContext context =
        new AnnotationConfigApplicationContext(EventConfig.class);
    DemoPublisher publisher = context.getBean(DemoPublisher.class);
    publisher.publish("hello application event");
    context.close();
  }
}
