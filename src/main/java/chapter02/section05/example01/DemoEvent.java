package chapter02.section05.example01;

import org.springframework.context.ApplicationEvent;

public class DemoEvent extends ApplicationEvent {
  private static final long serialVersionUID = -8711372193306973801L;

  private String msg;

  public DemoEvent(Object source, String msg) {
    super(source);
    this.msg = msg;
  }

  public String getMsg() {
    return msg;
  }

  public void setMsg(String msg) {
    this.msg = msg;
  }
}
