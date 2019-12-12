package com.github.mrzhqiang.springbootdata;

import com.github.mrzhqiang.springbootdata.entity.Person;
import com.github.mrzhqiang.springbootdata.service.DemoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CacheController {
  @Autowired
  private DemoService demoService;

  @RequestMapping("/put")
  public Person put(Person person) {
    return demoService.save(person);
  }

  @RequestMapping("/able")
  public Person cacheable(Person person) {
    return demoService.findOne(person);
  }

  @RequestMapping("/evit")
  public String evit(Long id) {
    demoService.remove(id);
    return "ok";
  }
}
