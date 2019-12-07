package com.github.mrzhqiang.springbootweb;

import java.util.ArrayList;
import org.apache.catalina.Context;
import org.apache.catalina.connector.Connector;
import org.apache.tomcat.util.descriptor.web.SecurityCollection;
import org.apache.tomcat.util.descriptor.web.SecurityConstraint;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.context.annotation.Bean;
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

  @Bean
  public TomcatServletWebServerFactory serverContainer() {
    TomcatServletWebServerFactory serverFactory = new TomcatServletWebServerFactory() {
      @Override protected void postProcessContext(Context context) {
        SecurityConstraint securityConstraint = new SecurityConstraint();
        securityConstraint.setUserConstraint("CONFIDENTIAL");
        SecurityCollection securityCollection = new SecurityCollection();
        securityCollection.addPattern("/*");
        securityConstraint.addCollection(securityCollection);
        context.addConstraint(securityConstraint);
      }
    };
    serverFactory.addAdditionalTomcatConnectors(initiateHttpConnector());
    return serverFactory;
  }

  private Connector initiateHttpConnector() {
    Connector connector = new Connector("org.apache.coyote.http11.Http11NioProtocol");
    connector.setScheme("http");
    connector.setPort(8080);
    connector.setSecure(false);
    connector.setRedirectPort(8443);
    return connector;
  }
}
