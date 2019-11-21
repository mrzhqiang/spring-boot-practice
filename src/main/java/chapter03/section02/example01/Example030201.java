package chapter03.section02.example01;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Example030201 {
  public static void main(String[] args) {
    AnnotationConfigApplicationContext context =
        new AnnotationConfigApplicationContext(TaskExecutorConfig.class);
    AsyncService asyncService = context.getBean(AsyncService.class);
    for (int i = 0; i < 10; i++) {
      asyncService.executeAsyncTask(i);
      asyncService.executeAsyncTaskPlus(i);
    }
    context.close();
  }
}
