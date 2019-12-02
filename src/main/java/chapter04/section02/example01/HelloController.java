package chapter04.section02.example01;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 运行此 Spring MVC 控制器的必备条件：
 * <p>
 * 1. 下载安装 Tomcat 容器
 * <p>
 * 2. 通过 IDEA 运行选项中编辑配置为 Tomcat Server >> Local
 * <p>
 * 3. 选择 Tomcat 容器安装的位置，并添加发布包，随后在 URL 一栏中添加 index
 * <p>
 * 4. 现在可以保存并运行这个控制器，稍后将自动打开浏览器访问此服务。
 * <p>
 * 注意：
 * <p>
 * 一般情况下，这个运行配置已添加到 .idea 目录下，所以可以直接运行。
 * <p>
 * 如果没有自动打开浏览器，请自行访问：
 * <p>
 * http://localhost:8080/spring_boot_practice_war_exploded/index
 */
@Controller
public class HelloController {
  @RequestMapping("/index")
  public String hello() {
    return "index";
  }
}
