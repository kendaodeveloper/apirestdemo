package com.example.apirestdemo.endpoint.swagger;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class SwaggerWebPage {
  @RequestMapping({"/", "/docs", "/swagger", "/swagger-ui"})
  public String redirect() {
    return "redirect:/swagger-ui.html";
  }
}