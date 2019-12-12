package com.github.mrzhqiang.springbootdata.repository;

import java.io.Serializable;
import javax.persistence.EntityManager;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.support.JpaRepositoryFactory;
import org.springframework.data.jpa.repository.support.JpaRepositoryFactoryBean;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.data.repository.core.RepositoryInformation;
import org.springframework.data.repository.core.RepositoryMetadata;
import org.springframework.data.repository.core.support.RepositoryFactorySupport;
import org.springframework.lang.NonNull;

public class CustomRepositoryFactoryBean<T extends JpaRepository<S, ID>, S, ID extends Serializable>
    extends JpaRepositoryFactoryBean<T, S, ID> {
  public CustomRepositoryFactoryBean(Class<? extends T> repositoryInterface) {
    super(repositoryInterface);
  }

  @Override
  protected RepositoryFactorySupport createRepositoryFactory(EntityManager entityManager) {
    return new CustomRepositoryFactory(entityManager);
  }

  private static final class CustomRepositoryFactory extends JpaRepositoryFactory {

    CustomRepositoryFactory(EntityManager entityManager) {
      super(entityManager);
    }

    @Override
    protected SimpleJpaRepository<?, ?> getTargetRepository(RepositoryInformation information,
        EntityManager entityManager) {
      return new CustomRepositoryImpl<>(information.getDomainType(), entityManager);
    }

    @Override protected Class<?> getRepositoryBaseClass(RepositoryMetadata metadata) {
      return CustomRepositoryImpl.class;
    }
  }
}
