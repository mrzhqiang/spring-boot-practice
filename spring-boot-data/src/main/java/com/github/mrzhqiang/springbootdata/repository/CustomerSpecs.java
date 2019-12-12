package com.github.mrzhqiang.springbootdata.repository;

import com.google.common.collect.Lists;
import java.lang.reflect.Field;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.criteria.Predicate;
import javax.persistence.metamodel.Attribute;
import javax.persistence.metamodel.EntityType;
import javax.persistence.metamodel.SingularAttribute;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.ReflectionUtils;
import org.springframework.util.StringUtils;

public class CustomerSpecs {
  public static <T> Specification<T> byAuto(EntityManager entityManager, T example) {
    return (Specification<T>) (root, criteriaQuery, criteriaBuilder) -> {
      List<Predicate> predicates = Lists.newArrayList();
      //noinspection unchecked
      EntityType<T> entity = entityManager.getMetamodel().entity((Class<T>) example.getClass());
      for (Attribute<T, ?> attribute : entity.getDeclaredAttributes()) {
        Object attrValue = getValue(example, attribute);
        if (attrValue != null) {
          if (attribute.getJavaType() == String.class) {
            if (!StringUtils.isEmpty(attrValue)) {
              predicates.add(criteriaBuilder.like(
                  root.get(attribute(entity, attribute.getName(), String.class)),
                  pattern((String) attrValue)));
            }
          } else {
            predicates.add(criteriaBuilder.equal(
                root.get(attribute(entity, attribute.getName(), attribute.getClass())),
                attrValue));
          }
        }
      }
      return predicates.isEmpty() ? criteriaBuilder.conjunction()
          : criteriaBuilder.and(predicates.toArray(new Predicate[] {}));
    };
  }

  private static <T> Object getValue(T example, Attribute<T, ?> attr) {
    return ReflectionUtils.getField((Field) attr.getJavaMember(), example);
  }

  private static <E, T> SingularAttribute<T, E> attribute(EntityType<T> entity, String fieldName,
      Class<E> fieldClass) {
    return entity.getDeclaredSingularAttribute(fieldName, fieldClass);
  }

  private static String pattern(String str) {
    return '%' + str + '%';
  }
}
