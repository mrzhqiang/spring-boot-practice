package com.github.mrzhqiang.springbootdata.service;

import com.github.mrzhqiang.springbootdata.entity.Person;
import com.github.mrzhqiang.springbootdata.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
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
}
