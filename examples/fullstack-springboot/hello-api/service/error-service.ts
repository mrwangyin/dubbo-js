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

import { Dubbo, TDubboCallResult } from 'apache-dubbo-consumer'
import { argumentMap } from 'interpret-util'

export interface IErrorProvider {
  errorTest(): TDubboCallResult<void>
}

export const ErrorProviderWrapper = { errorTest: argumentMap }

export const errorService = (dubbo: Dubbo): IErrorProvider =>
  dubbo.proxyService<IErrorProvider>({
    dubboInterface: 'org.apache.dubbo.demo.ErrorProvider',
    methods: ErrorProviderWrapper
  })
