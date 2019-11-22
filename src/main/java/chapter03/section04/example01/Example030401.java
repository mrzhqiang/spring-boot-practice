package chapter03.section04.example01;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Example030401 {
  public static void main(String[] args) {
    AnnotationConfigApplicationContext context =
        new AnnotationConfigApplicationContext(ConditionConfig.class);
    ListService listService = context.getBean(ListService.class);
    System.out.println(context.getEnvironment().getProperty("os.name")
        + " 系统下的列表命令为："
        + listService.showListCmd());
    context.close();
  }
}
