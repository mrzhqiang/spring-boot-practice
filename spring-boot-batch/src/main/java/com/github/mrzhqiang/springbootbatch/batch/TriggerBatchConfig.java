package com.github.mrzhqiang.springbootbatch.batch;

import com.github.mrzhqiang.springbootbatch.domain.Person;
import javax.sql.DataSource;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.launch.support.SimpleJobLauncher;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.repository.support.JobRepositoryFactoryBean;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.batch.item.validator.Validator;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
@EnableBatchProcessing
public class TriggerBatchConfig {
  /*@Bean
  public ItemReader<Person> reader() {
    FlatFileItemReader<Person> reader = new FlatFileItemReader<>();
    reader.setResource(new ClassPathResource("people.csv"));
    DefaultLineMapper<Person> lineMapper = new DefaultLineMapper<>();
    DelimitedLineTokenizer tokenizer = new DelimitedLineTokenizer();
    tokenizer.setNames("name", "age", "nation", "address");
    lineMapper.setLineTokenizer(tokenizer);
    BeanWrapperFieldSetMapper<Person> fieldSetMapper = new BeanWrapperFieldSetMapper<>();
    fieldSetMapper.setTargetType(Person.class);
    lineMapper.setFieldSetMapper(fieldSetMapper);
    reader.setLineMapper(lineMapper);
    return reader;
  }*/

  @Bean
  @StepScope
  public FlatFileItemReader<Person> reader(@Value("#{jobParameters['input.file.name']}") String pathToFile) {
    FlatFileItemReader<Person> reader = new FlatFileItemReader<>();
    reader.setResource(new ClassPathResource(pathToFile));
    DefaultLineMapper<Person> lineMapper = new DefaultLineMapper<>();
    DelimitedLineTokenizer tokenizer = new DelimitedLineTokenizer();
    tokenizer.setNames("name", "age", "nation", "address");
    lineMapper.setLineTokenizer(tokenizer);
    BeanWrapperFieldSetMapper<Person> fieldSetMapper = new BeanWrapperFieldSetMapper<>();
    fieldSetMapper.setTargetType(Person.class);
    lineMapper.setFieldSetMapper(fieldSetMapper);
    reader.setLineMapper(lineMapper);
    return reader;
  }

  @Bean
  public Validator<Person> csvBeanValidator() {
    return new CsvBeanValidator<>();
  }

  @Bean
  public ItemProcessor<Person, Person> processor() {
    CsvItemProcessor processor = new CsvItemProcessor();
    processor.setValidator(csvBeanValidator());
    return processor;
  }

  @Bean
  public ItemWriter<Person> writer(DataSource dataSource) {
    JdbcBatchItemWriter<Person> writer = new JdbcBatchItemWriter<>();
    writer.setItemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<>());
    String sql = "insert into person " + "(name,age,nation,address)"
        + "values(:name, :age, :nation, :address)";
    writer.setSql(sql);
    writer.setDataSource(dataSource);
    return writer;
  }

  @Bean
  public JobRepository jobRepository(DataSource dataSource,
      PlatformTransactionManager transactionManager) throws Exception {
    JobRepositoryFactoryBean jobRepositoryFactoryBean = new JobRepositoryFactoryBean();
    jobRepositoryFactoryBean.setDataSource(dataSource);
    jobRepositoryFactoryBean.setTransactionManager(transactionManager);
    jobRepositoryFactoryBean.setDatabaseType("mysql");
    return jobRepositoryFactoryBean.getObject();
  }

  @Bean
  public SimpleJobLauncher jobLauncher(DataSource dataSource,
      PlatformTransactionManager transactionManager) throws Exception {
    SimpleJobLauncher simpleJobLauncher = new SimpleJobLauncher();
    simpleJobLauncher.setJobRepository(jobRepository(dataSource, transactionManager));
    return simpleJobLauncher;
  }

  @Bean
  public CsvJobListener csvJobListener() {
    return new CsvJobListener();
  }

  @Bean
  public Job importJob(JobBuilderFactory jobs, Step s1) {
    return jobs.get("importJob")
        .incrementer(new RunIdIncrementer())
        .flow(s1)
        .end()
        .listener(csvJobListener())
        .build();
  }

  @Bean
  public Step step1(StepBuilderFactory stepBuilderFactory, ItemReader<Person> reader,
      ItemWriter<Person> writer, ItemProcessor<Person, Person> processor) {
    return stepBuilderFactory.get("step1")
        .<Person, Person>chunk(65000)
        .reader(reader)
        .processor(processor)
        .writer(writer)
        .build();
  }
}
