package org.apache.dubbo.demo.controller;


import org.apache.dubbo.config.ApplicationConfig;
import org.apache.dubbo.config.ReferenceConfig;
import org.apache.dubbo.config.RegistryConfig;
import org.apache.dubbo.config.context.ConfigManager;
import org.apache.dubbo.rpc.service.GenericService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/generic")
public class GenericCallController {

  private ApplicationConfig applicationConfig;

  private List<RegistryConfig> registryConfigs;

  @Autowired
  public GenericCallController(@Qualifier(ConfigManager.BEAN_NAME) ConfigManager configManager) {
    Optional<ApplicationConfig> application = configManager.getApplication();
    this.applicationConfig = application.orElseThrow(() -> new RuntimeException("ApplicationConfig not found"));
    this.registryConfigs = new ArrayList<>(configManager.getRegistries());
  }

  @GetMapping("/callJavaProvider")
  public Object callJavaProvider() {
    ReferenceConfig<GenericService> referenceConfig = new ReferenceConfig<>();
    referenceConfig.setInterface("org.apache.dubbo.demo.DemoProvider");
    referenceConfig.setApplication(applicationConfig);
    referenceConfig.setRegistries(registryConfigs);
    referenceConfig.setGeneric("true");
    referenceConfig.setAsync(false);
    referenceConfig.setVersion("1.0.0");
    GenericService genericService = referenceConfig.get();
    Object result = genericService.$invoke("sayHello", new String[]{"java.lang.String"}, new Object[]{"world"});
    System.out.println("GenericCall callJavaProvider result:" + result);
    return result;
  }

  @GetMapping("/callNodeJsProvider")
  public Object callNodeJsProvider() {
    try {
      ReferenceConfig<GenericService> referenceConfig = new ReferenceConfig<>();
      referenceConfig.setInterface("org.apache.dubbojs.service.TestService");
      referenceConfig.setApplication(applicationConfig);
      referenceConfig.setRegistries(registryConfigs);
      referenceConfig.setGeneric("true");
      referenceConfig.setAsync(false);
      referenceConfig.setVersion("1.0.0");
      GenericService genericService = referenceConfig.get();
      Object result = genericService.$invoke("goodBye", new String[]{"java.lang.String"}, new Object[]{"wangwu"});
      System.out.println("GenericCall callNodeJsProvider result:" + result);
      return result;
    } catch (Exception e) {
      return e.getMessage();
    }
  }

}
