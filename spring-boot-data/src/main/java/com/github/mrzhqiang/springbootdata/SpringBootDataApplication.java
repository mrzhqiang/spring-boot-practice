package com.github.mrzhqiang.springbootdata;

import com.github.mrzhqiang.springbootdata.repository.CustomRepositoryFactoryBean;
import com.github.mrzhqiang.springbootdata.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(repositoryFactoryBeanClass = CustomRepositoryFactoryBean.class)
public class SpringBootDataApplication {

  @Autowired
  private PersonRepository personRepository;

  public static void main(String[] args) {
    SpringApplication.run(SpringBootDataApplication.class, args);
  }
}
