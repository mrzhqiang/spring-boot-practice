package com.github.mrzhqiang.springbootweb;

import java.util.ArrayList;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@SpringBootApplication
public class SpringBootWebApplication {

  public static void main(String[] args) {
    SpringApplication.run(SpringBootWebApplication.class, args);
  }

  @RequestMapping("/")
  public String index(Model model) {
    Person single = new Person("aa", 11);
    ArrayList<Person> people = new ArrayList<>();
    people.add(new Person("xx", 11));
    people.add(new Person("yy", 22));
    people.add(new Person("zz", 33));
    model.addAttribute("singlePerson", single);
    model.addAttribute("people", people);
    return "index";
  }
}
