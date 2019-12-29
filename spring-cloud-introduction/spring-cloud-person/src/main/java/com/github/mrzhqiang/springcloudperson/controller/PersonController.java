package com.github.mrzhqiang.springcloudperson.controller;

import com.github.mrzhqiang.springcloudperson.dao.PersonRepository;
import com.github.mrzhqiang.springcloudperson.domain.Person;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PersonController {
  private final PersonRepository personRepository;

  @Autowired
  public PersonController(PersonRepository personRepository) {
    this.personRepository = personRepository;
  }

  @RequestMapping(value = "/save", method = RequestMethod.POST)
  public List<Person> save(@RequestBody String personName) {
    Person person = new Person(personName);
    personRepository.save(person);
    return personRepository.findAll(PageRequest.of(0, 10)).getContent();
  }
}
