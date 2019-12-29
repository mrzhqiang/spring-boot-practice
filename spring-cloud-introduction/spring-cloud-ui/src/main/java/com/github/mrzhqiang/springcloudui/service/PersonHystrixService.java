package com.github.mrzhqiang.springcloudui.service;

import com.github.mrzhqiang.springcloudui.domain.Person;
import com.google.common.collect.Lists;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PersonHystrixService {
  private final PersonService personService;

  @Autowired
  public PersonHystrixService(PersonService personService) {
    this.personService = personService;
  }

  @HystrixCommand(fallbackMethod = "fallbackSave")
  public List<Person> save(String name) {
    return personService.save(name);
  }

  public List<Person> fallbackSave(String name) {
    // todo 实际上应该返回一个空数组，然后数据应该用 restful 风格包装一下，在 message 中显示错误信息
    List<Person> list = Lists.newArrayList();
    Person person = new Person("Person service 故障");
    list.add(person);
    return list;
  }
}
