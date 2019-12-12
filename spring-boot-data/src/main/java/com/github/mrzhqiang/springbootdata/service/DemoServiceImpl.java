package com.github.mrzhqiang.springbootdata.service;

import com.github.mrzhqiang.springbootdata.entity.Person;
import com.github.mrzhqiang.springbootdata.repository.PersonRepository;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class DemoServiceImpl implements DemoService {
  @Autowired
  private PersonRepository personRepository;

  @Transactional(rollbackFor = {IllegalArgumentException.class})
  @Override public Person savePersonWithRollBack(Person person) {
    Person p = personRepository.save(person);
    if ("汪云飞".equals(person.getName())) {
      throw new IllegalArgumentException("汪云飞已存在，数据将回滚");
    }
    return p;
  }

  @Transactional(noRollbackFor = {IllegalArgumentException.class})
  @Override public Person savePersonWithoutRollBack(Person person) {
    Person p = personRepository.save(person);
    if ("汪云飞".equals(person.getName())) {
      throw new IllegalArgumentException("汪云飞虽然已存在，但数据不会回滚");
    }
    return p;
  }

  @CachePut(value = "people", key = "#person.id")
  @Override public Person save(Person person) {
    Person p = personRepository.save(person);
    System.out.println("给 ID 为 " + p.getId() + " 的数据做了缓存");
    return p;
  }

  @CacheEvict(value = "people")
  @Override public void remove(Long id) {
    System.out.println("删除 ID 为 " + id + " 的数据，清理缓存");
    personRepository.deleteById(id);
  }

  @Cacheable(value = "people", key = "#person.id")
  @Override public Person findOne(Person person) {
    Optional<Person> optional = personRepository.findById(person.getId());
    System.out.println("找到 ID 为 " + person.getId() + " 的数据，现在存入缓存");
    return optional.orElse(null);
  }
}
