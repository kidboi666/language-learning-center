package me.leejinwook.security1.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller // View를 리턴하겠다.
public class IndexController {

  @GetMapping({"","/"})
  public String index() {
    return "index";
  }
}