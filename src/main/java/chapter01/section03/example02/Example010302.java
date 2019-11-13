package chapter01.section03.example02;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public final class Example010302 {
  public static void main(String[] args) {
    AnnotationConfigApplicationContext context =
        new AnnotationConfigApplicationContext(JavaConfig.class);
    UseFunctionService useFunctionService = context.getBean(UseFunctionService.class);
    System.out.println(useFunctionService.sayHello("java config"));
    context.close();
  }
}
