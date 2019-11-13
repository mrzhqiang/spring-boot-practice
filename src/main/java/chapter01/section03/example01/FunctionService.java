package chapter01.section03.example01;

import org.springframework.stereotype.Service;

@Service
public final class FunctionService {
  public String sayHello(String word) {
    return String.format("Hello %s !", word);
  }
}
