package com.github.mrzhqiang.springbootdata;

import com.github.mrzhqiang.springbootdata.entity.Person;
import com.github.mrzhqiang.springbootdata.repository.PersonRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DataController {
  @Autowired
  private PersonRepository personRepository;

  @RequestMapping("/save")
  public Person save(String name, String address, Integer age) {
    return personRepository.save(new Person(name, age, address));
  }

  @RequestMapping("/q1")
  public List<Person> q1(String address) {
    return personRepository.findByAddress(address);
  }

  @RequestMapping("/q2")
  public Person q2(String name, String address) {
    return personRepository.findByNameAndAddress(name, address);
  }

  @RequestMapping("/q3")
  public Person q3(String name, String address) {
    return personRepository.withNameAndAddressQuery(name, address);
  }

  @RequestMapping("/q4")
  public Person q4(String name, String address) {
    return personRepository.withNameAndAddressNamedQuery(name, address);
  }

  @RequestMapping("/sort")
  public List<Person> sort() {
    return personRepository.findAll(Sort.by(Sort.Direction.ASC, "age"));
  }

  @RequestMapping("/page")
  public Page<Person> page() {
    return personRepository.findAll(PageRequest.of(1, 2));
  }

  @RequestMapping("/auto")
  public Page<Person> auto(Person person) {
    return personRepository.findByAuto(person, PageRequest.of(0, 10));
  }
}
