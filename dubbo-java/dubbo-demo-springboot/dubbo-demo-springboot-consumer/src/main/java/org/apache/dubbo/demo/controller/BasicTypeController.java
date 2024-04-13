package org.apache.dubbo.demo.controller;

import org.apache.dubbo.config.annotation.DubboReference;
import org.apache.dubbo.demo.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/basicType")
public class BasicTypeController {

  @DubboReference(version = "2.0.0")
  private BasicTypeProvider basicTypeProvider;

  @GetMapping("/testBasicType")
  public TypeRequest testBasicType() {
    TypeRequest request = new TypeRequest();
    request.setBigDecimal(new BigDecimal("3.1415926"));
    Map<String, String> map = new HashMap<>();
    map.put("k1", "v1");
    request.setMap(map);
    return basicTypeProvider.testBasicType(request);
  }

}
