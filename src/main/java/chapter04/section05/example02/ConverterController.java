package chapter04.section05.example02;

import chapter04.section03.example01.DemoObj;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ConverterController {
  @RequestMapping(value = "/convert", produces = {"application/x-wisely"})
  public @ResponseBody DemoObj convert(@RequestBody DemoObj demoObj) {
    return demoObj;
  }
}
