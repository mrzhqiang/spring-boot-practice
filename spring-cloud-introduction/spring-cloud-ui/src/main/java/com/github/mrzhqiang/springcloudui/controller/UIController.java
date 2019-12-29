package com.github.mrzhqiang.springcloudui.controller;

import com.github.mrzhqiang.springcloudui.domain.Person;
import com.github.mrzhqiang.springcloudui.service.PersonHystrixService;
import com.github.mrzhqiang.springcloudui.service.SomeHystrixService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UIController {
  private final PersonHystrixService personHystrixService;
  private final SomeHystrixService someHystrixService;

  @Autowired
  public UIController(
      PersonHystrixService personHystrixService,
      SomeHystrixService someHystrixService) {
    this.personHystrixService = personHystrixService;
    this.someHystrixService = someHystrixService;
  }

  @RequestMapping("/dispatch")
  public List<Person> sendMessage(@RequestBody String personName) {
    return personHystrixService.save(personName);
  }

  @RequestMapping(value = "/getsome", produces = MediaType.TEXT_PLAIN_VALUE)
  public String getSome() {
    return someHystrixService.getSome();
  }
}
