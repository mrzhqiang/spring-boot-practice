package chapter02.section05.example01;

import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class DemoListener implements ApplicationListener<DemoEvent> {

  @Override public void onApplicationEvent(DemoEvent event) {
    System.out.println("我(bean-"
        + this.getClass().getSimpleName()
        + ")接收到了 bean-"
        + event.getSource().getClass().getSimpleName()
        + " 发布的消息："
        + event.getMsg());
  }
}
