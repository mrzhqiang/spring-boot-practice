package com.github.mrzhqiang.springbootdata.repository;

import java.io.Serializable;
import javax.persistence.EntityManager;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;

public class CustomRepositoryImpl<T, ID extends Serializable> extends SimpleJpaRepository<T, ID>
    implements CustomRepository<T, ID> {
  private final EntityManager entityManager;

  public CustomRepositoryImpl(Class<T> domainClass, EntityManager em) {
    super(domainClass, em);
    this.entityManager = em;
  }

  @Override public Page<T> findByAuto(T example, Pageable pageable) {
    return findAll(CustomerSpecs.byAuto(entityManager, example), pageable);
  }
}
