package com.github.mrzhqiang.springbootbatch.batch;

import com.github.mrzhqiang.springbootbatch.domain.Person;
import org.springframework.batch.item.validator.ValidatingItemProcessor;
import org.springframework.batch.item.validator.ValidationException;

public class CsvItemProcessor extends ValidatingItemProcessor<Person> {
  @Override public Person process(Person item) throws ValidationException {
    Person process = super.process(item);
    if (process != null) {
      if ("汉族".equals(process.getNation())) {
        process.setNation("01");
      } else {
        process.setNation("02");
      }
    }
    return process;
  }
}
