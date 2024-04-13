package org.apache.dubbo.demo.controller;

import org.apache.dubbo.config.annotation.DubboReference;
import org.apache.dubbo.demo.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/error")
public class ErrorController {

  @DubboReference(version = "1.0.0")
  private ErrorProvider demoProvider;

  @GetMapping("/errorTest")
  public void errorTest() throws CustomizeException {
    demoProvider.errorTest();
  }

}
