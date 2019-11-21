package chapter03.section02.example01;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class AsyncService {
  @Async
  public void executeAsyncTask(Integer i) {
    System.out.println("执行异步任务：" + i);
  }

  @Async
  public void executeAsyncTaskPlus(Integer i) {
    System.out.println("执行异步任务 +1：" + (i + 1));
  }
}
