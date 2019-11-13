package chapter01.section03.example02;

public final class UseFunctionService {
  private FunctionService functionService;

  public void setFunctionService(FunctionService functionService) {
    this.functionService = functionService;
  }

  public String sayHello(String word) {
    return functionService.sayHello(word);
  }
}
