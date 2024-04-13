#### 启用 dubbo java provider 使用 nacos 注册中心

```shell
cd nacos
java -jar dubbo-demo-springboot-provider-1.0-SNAPSHOT.jar
```

#### 启用 dubbo java consumer 使用 nacos 注册中心

```shell
cd nacos
java -jar dubbo-demo-springboot-consumer-1.0-SNAPSHOT.jar
```

#### 测试 dubbo-java-consumer -> dubbo-java-provider

##### [demo-controller.http](dubbo-demo-springboot-consumer%2Fhttp%2Fdemo-controller.http)

```shell
curl http://localhost:9999/demo/test
```

```shell
curl http://localhost:9999/demo/sayHello?var1=world
```

```shell
curl http://localhost:9999/demo/echo
```

```shell
curl http://localhost:9999/demo/getUserInfo
```

##### [basic-type-controller.http](dubbo-demo-springboot-consumer%2Fhttp%2Fbasic-type-controller.http)

```shell
curl http://localhost:9999/basicType/testBasicType
```

##### [basic-type-controller.http](dubbo-demo-springboot-consumer%2Fhttp%2Fbasic-type-controller.http)

```shell
curl http://localhost:9999/generic/callJavaProvider
```
