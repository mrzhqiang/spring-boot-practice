package com.github.mrzhqiang.springbootsecurity.controller;

import com.github.mrzhqiang.springbootsecurity.model.Msg;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HomeController {
  @RequestMapping("/")
  @ResponseBody
  public String index() {
    return "Hello Security";
  }

  @RequestMapping("/home")
  public String home(Model model) {
    Msg msg = new Msg("测试标题", "测试内容", "额外信息，只对管理员显示");
    model.addAttribute("msg", msg);
    return "home";
  }

  @RequestMapping("/login")
  public String login() {
    return "login";
  }
}
