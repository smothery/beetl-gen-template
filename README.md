# beetl-gen-template
基于beetlsql 快速生成controller,service,serviceImpl,方法出入参数实体,dao,entity,sql


目前只定义了easyopen的模板，去掉easyopen的注释也可以适用普通的mvc。

## 使用方法

1.修改application-dev.yml的数据库连接配置

```
spring:
    # 数据库访问配置
    datasource:
        driver-class-name: org.postgresql.Driver
        url: jdbc:postgresql://localhost:5432/es_test
        username: postgres
        password: 123456
```

2.执行生成方法
* src/test/java/panzer/vor/generate/GenerateTest.java 内的easyopen_gen_all() 方法

```
@Test
public void easyopen_gen_all() throws Exception {
```

## 扩展

##### 项目结构
```
|-- src
|  |-- main
|  |    |-- java
|  |    |   |-- panzer.vor.generate.easyopen // 生成的Java代码
|  |    |-- resources
|  |    |   |-- sql // 生成的sql文件
|  |-- test
|  |    |-- java
|  |    |   |-- panzer.vor.generate.easyopen // 代码生成具体实现，与模板一一对应
|  |    |-- resources
|  |    |   |-- easyopen // 代码模板，与代码生成具体实现一一对应
```

##### 扩展实现
1. 在test\java\panzer\vor\generate\easyopen 增加自己的具体实现
2. 在test\resources\easyopen 定义自己的代码模板,并在自己的具体实现内引入
3. 把定义好的具体实现引入到easyopen_gen_all()方法
```
@Test
public void easyopen_gen_all() throws Exception {
    GenConfig config = new GenConfig("/easyopen/ext_pojo.btl");
    MapperCodeGen mapperCodeGen = new MapperCodeGen();
    mapperCodeGen.setMapperTemplate((new GenConfig()).getTemplate("/easyopen/ext_mapper.btl"));
    config.codeGens.add(mapperCodeGen);
    config.codeGens.add(new DTOCodeGen(sqlManager));
    config.codeGens.add(new ApiCodeGen());
    config.codeGens.add(new ServiceCodeGen());
    config.codeGens.add(new ServiceImplCodeGen());
    config.codeGens.add(new ListRequestParamsCodeGen(sqlManager));
    config.codeGens.add(new CreateRequestParamsCodeGen(sqlManager));
    config.codeGens.add(new UpdateRequestParamsCodeGen(sqlManager));
    config.codeGens.add(new 你的具体实现(sqlManager));
```
