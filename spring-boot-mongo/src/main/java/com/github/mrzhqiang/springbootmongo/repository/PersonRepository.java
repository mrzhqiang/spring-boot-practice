package com.github.mrzhqiang.springbootmongo.repository;

import com.github.mrzhqiang.springbootmongo.entity.Person;
import java.util.List;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

public interface PersonRepository extends MongoRepository<Person, String> {
  Person findByName(String name);

  @Query("{'age': ?0}")
  List<Person> withQueryFindByAge(Integer age);
}
