package com.github.mrzhqiang.springcloudperson.dao;

import com.github.mrzhqiang.springcloudperson.domain.Person;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepository extends JpaRepository<Person, Long> {
}
