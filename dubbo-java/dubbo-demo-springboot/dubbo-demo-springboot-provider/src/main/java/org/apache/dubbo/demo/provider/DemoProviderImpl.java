/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.apache.dubbo.demo.provider;

import com.alibaba.dubbo.rpc.RpcContext;
import com.alibaba.fastjson.JSON;
import org.apache.dubbo.config.annotation.DubboService;
import org.apache.dubbo.config.annotation.Method;
import org.apache.dubbo.demo.DemoProvider;
import org.apache.dubbo.demo.UserRequest;
import org.apache.dubbo.demo.UserResponse;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;


@DubboService(version = "1.0.0", timeout = 10000, methods = {@Method(name = "sayHello", timeout = 8000)})
public class DemoProviderImpl implements DemoProvider {

  public String sayHello(String name) {
    System.out.println("[" + new SimpleDateFormat("HH:mm:ss").format(new Date()) + "] Hello " + name + ", request from consumer: " + RpcContext.getContext().getRemoteAddress());
    return "Hello " + name + ", response form provider: " + RpcContext.getContext().getLocalAddress();
  }

  @Override
  public String echo() {
    System.out.println("[" + new SimpleDateFormat("HH:mm:ss").format(new Date()) + "] DemoProvider#echo " + ", request from consumer: " + RpcContext.getContext().getRemoteAddress());
    return "pang";
  }

  @Override
  public void test() {
    System.out.println("[" + new SimpleDateFormat("HH:mm:ss").format(new Date()) + "] DemoProvider#test " + ", request from consumer: " + RpcContext.getContext().getRemoteAddress());
    System.out.println("test");
  }

  @Override
  public UserResponse getUserInfo(UserRequest request) {
    System.out.println("[" + new SimpleDateFormat("HH:mm:ss").format(new Date()) + "] DemoProvider#getUserInfo request:"+JSON.toJSONString(request) + ", request from consumer: " + RpcContext.getContext().getRemoteAddress());
    UserResponse response = new UserResponse();
    response.setStatus("ok");
    Map<String, String> map = new HashMap<String, String>();
    map.put("id", "1");
    map.put("name", "test");
    response.setInfo(map);
    return response;
  }

}
