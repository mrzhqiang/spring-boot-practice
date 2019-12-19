package com.github.mrzhqiang.springbootmongo;

import com.github.mrzhqiang.springbootmongo.entity.Location;
import com.github.mrzhqiang.springbootmongo.entity.Person;
import com.github.mrzhqiang.springbootmongo.repository.PersonRepository;
import java.util.LinkedHashSet;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DataController {
  @Autowired
  private PersonRepository personRepository;

  @RequestMapping("/save")
  public Person save() {
    Person person = new Person("wyf", 32);
    LinkedHashSet<Location> locations = new LinkedHashSet<>();
    locations.add(new Location("shanghai", "2009"));
    locations.add(new Location("hefei", "2010"));
    locations.add(new Location("guangzhou", "2011"));
    locations.add(new Location("maanshan", "2012"));
    person.setLocations(locations);
    return personRepository.save(person);
  }

  @RequestMapping("/q1")
  public Person q1(String name) {
    return personRepository.findByName(name);
  }

  @RequestMapping("/q2")
  public List<Person> q2(Integer age) {
    return personRepository.withQueryFindByAge(age);
  }
}
