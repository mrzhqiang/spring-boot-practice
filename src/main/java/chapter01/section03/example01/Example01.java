package chapter01.section03.example01;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public final class Example01 {
  public static void main(String[] args) {
    AnnotationConfigApplicationContext context =
        new AnnotationConfigApplicationContext(DiConfig.class);
    UseFunctionService functionServer = context.getBean(UseFunctionService.class);
    System.out.println(functionServer.sayHello("di"));
    context.close();
  }
}
