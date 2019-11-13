package chapter01.section03.example01;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public final class UseFunctionService {
  @Autowired
  FunctionService functionService;

  public String sayHello(String word) {
    return functionService.sayHello(word);
  }
}
