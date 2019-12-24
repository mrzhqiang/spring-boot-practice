package com.github.mrzhqiang.springbootrest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.mrzhqiang.springbootrest.entity.Person;
import com.github.mrzhqiang.springbootrest.repository.PersonRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

@SpringBootTest
@Transactional
class SpringBootRestApplicationTests {

  @Autowired PersonRepository personRepository;
  MockMvc mvc;
  @Autowired
  WebApplicationContext webApplicationContext;
  String expectedJson;

  @BeforeEach
  void setup() throws JsonProcessingException {
    Person wyf = new Person(null, "wyf", null, null);
    Person wisely = new Person(null, "wisely", null, null);
    personRepository.save(wyf);
    personRepository.save(wisely);
    ObjectMapper objectMapper = new ObjectMapper();
    expectedJson = objectMapper.writerWithDefaultPrettyPrinter()
        .writeValueAsString(personRepository.findAll());
    mvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
  }

  @Test
  void testPersonController() throws Exception {
    String uri = "/person";
    MvcResult mvcResult =
        mvc.perform(MockMvcRequestBuilders.get(uri).accept(MediaType.APPLICATION_JSON_VALUE))
            .andReturn();
    int status = mvcResult.getResponse().getStatus();
    mvcResult.getResponse().setCharacterEncoding("UTF-8");
    String content = mvcResult.getResponse().getContentAsString();

    Assertions.assertEquals(200, status, "错误，正确的返回值为 200");
    Assertions.assertEquals(expectedJson, content, "错误，返回值和预期返回值不一致");
  }

  @Test
  void contextLoads() {
  }
}
