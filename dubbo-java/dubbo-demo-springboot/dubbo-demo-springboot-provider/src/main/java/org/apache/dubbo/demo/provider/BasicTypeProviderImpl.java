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
import org.apache.dubbo.demo.BasicTypeProvider;
import org.apache.dubbo.demo.TypeRequest;

import java.text.SimpleDateFormat;
import java.util.Date;

@DubboService(version = "2.0.0")
public class BasicTypeProviderImpl implements BasicTypeProvider {
  @Override
  public TypeRequest testBasicType(TypeRequest request) {
    System.out.println("[" + new SimpleDateFormat("HH:mm:ss").format(new Date()) + "] BasicTypeProvider#testBasicType request:" + JSON.toJSONString(request) + ", request from consumer: " + RpcContext.getContext().getRemoteAddress());
    return request;
  }
}
