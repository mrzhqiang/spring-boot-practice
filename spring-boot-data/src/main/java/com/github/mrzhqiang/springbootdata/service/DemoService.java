package com.github.mrzhqiang.springbootdata.service;

import com.github.mrzhqiang.springbootdata.entity.Person;

public interface DemoService {
  Person savePersonWithRollBack(Person person);

  Person savePersonWithoutRollBack(Person person);

  Person save(Person person);

  void remove(Long id);

  Person findOne(Person person);
}
