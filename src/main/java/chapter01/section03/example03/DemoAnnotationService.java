package chapter01.section03.example03;

import org.springframework.stereotype.Service;

@Service
public class DemoAnnotationService {
  @Action(name = "注解式拦截的 add 操作")
  public void add() {
  }
}
