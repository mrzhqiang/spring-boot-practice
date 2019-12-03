package chapter04.section04.example01;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class DemoInterceptor extends HandlerInterceptorAdapter {
  private static final Logger LOGGER = LoggerFactory.getLogger(DemoInterceptor.class);

  @Override
  public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
      throws Exception {
    long startTime = System.currentTimeMillis();
    request.setAttribute("startTime", startTime);
    return true;
  }

  @Override
  public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
      ModelAndView modelAndView) throws Exception {
    Long startTime = (Long) request.getAttribute("startTime");
    request.removeAttribute("startTime");
    long endTime = System.currentTimeMillis();
    LOGGER.info("本次请求处理时间为：{}ms", endTime - startTime);
    request.setAttribute("handlingTime", endTime - startTime);
  }
}
