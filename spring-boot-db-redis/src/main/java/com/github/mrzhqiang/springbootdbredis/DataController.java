package com.github.mrzhqiang.springbootdbredis;

import com.github.mrzhqiang.springbootdbredis.dao.PersonDao;
import com.github.mrzhqiang.springbootdbredis.entity.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DataController {
  @Autowired PersonDao personDao;

  @RequestMapping("/set")
  public void set() {
    Person person = new Person("1", "wyf", 32);
    personDao.save(person);
    personDao.stringRedisTemplateDemo();
  }

  @RequestMapping("/getStr")
  public String getStr() {
    return personDao.getString();
  }

  @RequestMapping("/getPerson")
  public Person getPerson() {
    return personDao.getPerson();
  }
}
