package com.github.mrzhqiang.springbootbatch.batch;

import java.util.Set;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.ValidatorFactory;
import org.springframework.batch.item.validator.ValidationException;
import org.springframework.batch.item.validator.Validator;
import org.springframework.beans.factory.InitializingBean;

public class CsvBeanValidator<T> implements Validator<T>, InitializingBean {

  private javax.validation.Validator validator;

  @Override public void validate(T t) throws ValidationException {
    Set<ConstraintViolation<T>> validate = validator.validate(t);
    if (validate.size() > 0) {
      StringBuilder stringBuilder = new StringBuilder();
      for (ConstraintViolation<T> violation : validate) {
        stringBuilder.append(violation.getMessage()).append("\n");
      }
      throw new ValidationException(stringBuilder.toString());
    }
  }

  @Override public void afterPropertiesSet() throws Exception {
    ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
    validator = validatorFactory.usingContext().getValidator();
  }
}
