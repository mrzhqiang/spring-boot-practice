package com.github.mrzhqiang.springbootcore;

import com.github.mrzhqiang.HelloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class SpringBootCoreApplication {

  public static void main(String[] args) {
    SpringApplication.run(SpringBootCoreApplication.class, args);
    //SpringApplication app = new SpringApplication(SpringBootCoreApplication.class);
    //app.setBannerMode(Banner.Mode.OFF);
    //app.run(args);
    //new SpringApplicationBuilder(SpringBootCoreApplication.class)
    //    .bannerMode(Banner.Mode.OFF)
    //    .run(args);
  }

  //@Value("${book.author}")
  //private String bookAuthor;
  //@Value("${book.name}")
  //private String bookName;
  @Autowired
  private AuthorSettings authorSettings;

  @Autowired
  private HelloService helloService;

  @RequestMapping("/")
  public String index() {
    //return "book name is: " + bookName + ", and book author is: " + bookAuthor;
    //return "author name is "
    //    + authorSettings.getName()
    //    + ", and author age is "
    //    + authorSettings.getAge();
    return helloService.sayHello();
  }
}
