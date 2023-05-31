# 一, Spring系统架构
1. Core Container: 核心容器 --- Beans  Core  Context  SpEL
2. Data Access: 数据访问/Data Integration: 数据集成
3. AOP: 面向切面编程
4. Aspects: AOP思想实现
5. Transactions: 事务  (归于Data Access里)
6. Web: Web开发
7. Test: 单元测试与集成测试

# 二, IoC思想和DI(依赖注入) 目标: 充分解耦
## 1.  IoC思想 --- (Inversion of Control)控制反转
对象的创建控制权由程序转移到外部
使用对象时, 程序不要主动使用new产生对象, 转换为由外部提供对象
Spring技术对Ioc思想进行了思想, 提供了一个IoC容器, 用来充当IoC思想的外部
IoC容器负责对象的创建, 初始化等一系列工作, 被创建或被管理的对象在IoC容器中统称为Bean
## 2. DI(依赖注入)
在容器中建立bean与bean之间的依赖关系的整个过程

最终效果: 使用对象时不仅可以直接从IoC容器中获取, 并且获取到的bean已经绑定了所有的依赖关系

## 3. IoC 获取方法
**配置**
**接口**
**接口方法**

# 三, bean配置
## 1. bean基础配置:

-  bean标签表示配置bean
-  id属性表示给bean起名字
-  class属性表示给bean定义类型
-  id不能重复

```xml
    <bean id="bookDao" class="com.itstudy.dao.impl.BookDaoImpl"/>

    <bean id="bookService" class="com.itstudy.service.impl.BookServiceImpl"/>
```
## 2. bean的别名:
通过bean标签的name属性可以起多个别名
```xml
    <bean id="bookService" name="service service01" class="com.itstudy.service.impl.BookServiceImpl"/>
```

## 3. bean的作用范围:
spring创造出的对象是单例的, 对象只有一个
```java
   System.out.println(bookService1); //@865dd6
   System.out.println(bookService2); //@865dd6
```

通过修改bean的scope属性 scope="prototype" 可以产生不同的对象@4da4253

```xml
    <bean id="bookDao" class="com.itstudy.dao.impl.BookDaoImpl" scope="prototype"/>
```

为什么bean默认为单例呢?

- 多次创造对象会对造成业务逻辑复杂, 对内存造成压力
- 单例适合业务运行时从容器里多次拿出来用一次再放回, 减少内存的运行压力

**适合交给容器管理的bean**
- 表现层对象
- 业务层对象
- 数据层对象
- 工具对象

**不适合交给容器管理的bean**
-  封装实体的域对象

___
