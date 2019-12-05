package chapter04.section05.example03;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.async.DeferredResult;

@Controller
public class AsyncController {
  @Autowired
  PushService pushService;

  @RequestMapping(value = "defer")
  @ResponseBody
  public DeferredResult<String> deferredResult() {
    return pushService.getDeferredResult();
  }
}
