package chapter02.section01.example01;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Example020101 {
  public static void main(String[] args) {
    AnnotationConfigApplicationContext context =
        new AnnotationConfigApplicationContext(ScopeConfig.class);
    DemoSingletonService singletonService = context.getBean(DemoSingletonService.class);
    DemoPrototypeService prototypeService = context.getBean(DemoPrototypeService.class);
    // true
    System.out.println("singleton service instance is singleton: " + singletonService.equals(
        context.getBean(DemoSingletonService.class)));
    // false
    System.out.println("prototype service instance is singleton: " + prototypeService.equals(
        context.getBean(DemoPrototypeService.class)));
    context.close();
  }
}
