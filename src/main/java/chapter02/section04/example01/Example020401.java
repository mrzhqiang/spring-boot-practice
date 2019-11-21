package chapter02.section04.example01;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Example020401 {
  public static void main(String[] args) {
    AnnotationConfigApplicationContext context =
        new AnnotationConfigApplicationContext();
    context.getEnvironment().setActiveProfiles("prod");
    context.register(ProfileConfig.class);
    context.refresh();

    DemoBean demoBean = context.getBean(DemoBean.class);
    System.out.println(demoBean.getContent());

    context.close();
  }
}
