package org.apache.dubbo.demo.controller;

import org.apache.dubbo.config.annotation.DubboReference;
import org.apache.dubbo.demo.DemoProvider;
import org.apache.dubbo.demo.Sex;
import org.apache.dubbo.demo.UserRequest;
import org.apache.dubbo.demo.UserResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/demo")
public class DemoController {

  @DubboReference(version = "1.0.0")
  private DemoProvider demoProvider;

  @GetMapping("/test")
  public void test(){
    demoProvider.test();
  }

  @GetMapping("/sayHello")
  public String sayHello(@RequestParam String var1){
    return demoProvider.sayHello(var1);
  }

  @GetMapping("/echo")
  public String echo(){
    return demoProvider.echo();
  }

  @GetMapping("/getUserInfo")
  public UserResponse getUserInfo(){
    UserRequest request = new UserRequest();
    request.setId(100001);
    request.setName("zhangsan");
    request.setSex(Sex.female);
    request.setEmail("a@qq.com");
    return demoProvider.getUserInfo(request);
  }

}
