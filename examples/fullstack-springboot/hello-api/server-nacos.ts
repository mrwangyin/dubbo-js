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

import Koa from 'koa'
import Router from 'koa-router'
import { Dubbo, s } from 'apache-dubbo-consumer'
import { Nacos } from 'apache-dubbo-registry'
import services from './service'

s.service(
  ['org.apache.dubbo.demo.DemoProvider', 'org.apache.dubbo.demo.ErrorProvider'],
  {
    version: '1.0.0'
  }
)

s.service('org.apache.dubbo.demo.BasicTypeProvider', { version: '2.0.0' })

const dubbo = new Dubbo<typeof services>({
  application: {
    name: 'hello-api'
  },
  registry: Nacos({
    // connect: '124.71.231.54:8848?username=nacos&password=nacos',
    connect: '124.71.231.54:8848',
    namespace: 'dubbojs4',
    logger: console
  }),
  services
})

const server = new Koa()
const router = new Router()

router.get('/hello', async (ctx) => {
  const { res, err } = await dubbo.service.helloService.hello('dubbo-js')
  ctx.body = {
    res,
    err: err?.message
  }
})

router.post('/demo/sayHello', async (ctx) => {
  const { res, err } = await dubbo.service.demoService.sayHello(
    'dubbojs-rpc-java'
  )
  ctx.body = {
    res,
    err: err?.message
  }
})

// 将路由中间件应用到Koa应用中
server.use(router.routes()).use(router.allowedMethods())

server.listen(6000)
console.log('hello-api start at port 6000....')
