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

import { Dubbo, TDubboCallResult, java } from 'apache-dubbo-consumer'
import { argumentMap, JavaString } from 'interpret-util'

import { UserRequest } from './UserRequest'
import { UserResponse } from './UserResponse'

export interface IDemoProvider {
  sayHello(name: string): Promise<TDubboCallResult<string>>
  // test(): TDubboCallResult<void>
  // echo(): TDubboCallResult<string>
  // getUserInfo(request: UserRequest): TDubboCallResult<UserResponse>
}

// export const DemoProviderWrapper = {
//   sayHello: argumentMap,
//   test: argumentMap,
//   echo: argumentMap,
//   getUserInfo: argumentMap
// }

export const demoService = (dubbo: Dubbo): IDemoProvider =>
  dubbo.proxyService<IDemoProvider>({
    dubboInterface: 'org.apache.dubbo.demo.DemoProvider',
    // methods: DemoProviderWrapper
    // 因为调用时报错： "err": "Cannot read property 'path' of undefined"
    // 所以添加了 path，但是仍然报错
    path: 'org.apache.dubbo.demo.DemoProvider',
    methods: {
      sayHello(name: string) {
        return [java.String(name)]
      }
    }
  })
