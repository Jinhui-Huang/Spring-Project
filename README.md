<!-- TOC -->
* [一. Spring系统架构](#一-spring系统架构)
* [二. IoC思想和DI(依赖注入) 目标: 充分解耦](#二-ioc思想和di依赖注入-目标-充分解耦)
  * [1. IoC思想 --- (Inversion of Control)控制反转](#1-ioc思想-----inversion-of-control控制反转)
  * [2. DI(依赖注入)](#2-di依赖注入)
  * [3. IoC 获取方法](#3-ioc-获取方法)
* [三. bean配置](#三-bean配置)
  * [1. bean基础配置:](#1-bean基础配置)
  * [2. bean的别名:](#2-bean的别名)
  * [3. bean的作用范围:](#3-bean的作用范围)
  * [4. bean的实例化](#4-bean的实例化)
  * [5. bean的生命周期](#5-bean的生命周期)
    * [详细生命周期](#详细生命周期)
* [四. 依赖注入方式](#四-依赖注入方式)
  * [1. setter注入](#1-setter注入)
  * [2. 构造器注入](#2-构造器注入)
  * [3. 依赖注入方式选择](#3-依赖注入方式选择)
  * [4. 依赖自动装配](#4-依赖自动装配)
  * [5. 集合注入](#5-集合注入)
* [五. 数据源对象管理(第三方资源配置管理)](#五-数据源对象管理第三方资源配置管理)
  * [1. 以阿里巴巴的druid数据源导入为例:](#1-以阿里巴巴的druid数据源导入为例)
  * [2. 以c3p0数据源导入为例](#2-以c3p0数据源导入为例)
  * [3. 加载Properties文件形式开启数据源(对druid源修改)](#3-加载properties文件形式开启数据源对druid源修改)
* [六. 容器](#六-容器)
  * [1. 创建容器](#1-创建容器)
  * [2. 获取bean](#2-获取bean)
  * [3. 容器类层次结构](#3-容器类层次结构)
  * [4. BeanFactory](#4-beanfactory)
<!-- TOC -->

# 一. Spring系统架构

1. Core Container: 核心容器 --- Beans Core Context SpEL
2. Data Access: 数据访问/Data Integration: 数据集成
3. AOP: 面向切面编程
4. Aspects: AOP思想实现
5. Transactions: 事务  (归于Data Access里)
6. Web: Web开发
7. Test: 单元测试与集成测试

# 二. IoC思想和DI(依赖注入) 目标: 充分解耦

## 1. IoC思想 --- (Inversion of Control)控制反转

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

# 三. bean配置

## 1. bean基础配置:

- bean标签表示配置bean
- id属性表示给bean起名字
- class属性表示给bean定义类型
- id不能重复

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
class App {
    public static void main(String[] args) {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
        BookService bookService1 = (BookService) ctx.getBean("bookService");
        BookService bookService2 = (BookService) ctx.getBean("bookService");
        System.out.println(bookService1); //@865dd6
        System.out.println(bookService2); //@865dd6
    }
}
```

通过修改bean的scope属性 scope="prototype" 可以产生不同的对象@4da4253

```xml

<bean id="bookDao" class="com.itstudy.dao.impl.BookDaoImpl" scope="prototype"/>
```

为什么bean默认为单例呢?

- 多次创造对象会造成业务逻辑复杂, 对内存造成压力
- 单例适合业务运行时多次从容器里拿出同一bean对象, 用完就放回去, 减少内存的运行压力

**适合交给容器管理的bean**

- 表现层对象
- 业务层对象
- 数据层对象
- 工具对象

**不适合交给容器管理的bean**

- 封装实体的域对象

## 4. bean的实例化

(1) bean本质上是对象, spring内部通过反射来构造对应的bean对象, 如果bean对象只有有参构造方法,
spring构造会报BeanCreateException

- spring采用的是通过反射进行的无参构造
- 解决spring异常从异常底部开始解决, 大部分情况解决最底下的异常就能解决

(2)bean实例化--静态工厂

- 实例化工厂时必须实例化它的方法

```xml
<!--方法二: 使用静态工厂实例化bean-->
<bean id="orderDao" class="com.itstudy.factory.OrderDaoFactory" factory-method="getOrderDao"/>
```

(3)bean实例化--实例工厂

- 先实现实例工厂对象, 再实现实例工厂里的方法

```xml
<!--方法三: 使用实例工厂实例化bean-->
<bean id="userFactory" class="com.itstudy.factory.UseDaoFactory"/>
        <!--userFactory完全是配合使用, 实际无意义-->
<bean id="userDao" factory-bean="userFactory" factory-method="getUserDao"/>
- ```

(4)针对(3)实现的简单方法

- 新建一个UserDaoFactoryBean继承接口FactoryBean<T>, T为你要实现的类型

```java
public class UserDaoFactoryBean implements FactoryBean<UserDao> {
    //代替原始实例工厂中创建对象的方法
    @Override
    public UserDao getObject() throws Exception {
        return new UserDaoImpl();
    }
    //返回你要创造类的类型字节码class(一般接口的class)
    @Override
    public Class<?> getObjectType() {
        return UserDao.class;
    }
}
```

- 然后在application中配置一个Factory的bean就行

```xml
<!--方法四(方法三的变种)-->
<bean id="userDao" class="com.itstudy.factory.UserDaoFactoryBean"/>
```

- 实际新建出来的对象是getobject方法里实现出来的对象, 非工厂对象, FactoryBean是后期非常常用的一种构建bean的方法

## 5. bean的生命周期

- 生命周期: 从创建到消亡的过程
- bean生命周期: bean从创建到消亡的过程
- bean生命周期控制: 在bean创建后到销毁前做一些事情, 例如在bean创建之前进行数据的准备

(1)实现方式: 写好对应的初始化和销毁方法, 然后在配置文件中进行配置

```xml

<bean id="bookDao" class="com.itstudy.dao.impl.BookDaoImpl" init-method="init" destroy-method="destroy"/>
```

ClassPathXmlApplicationContext里存在暴力关闭的方法close(),建议采用关闭钩子的方法registerShutdownHook();后期web工程会应用到
这些方法均来自于ConfigurableApplicationContext接口

(2)spring提供的接口InitializingBean, DisposableBean里的方法,分别重写下面两个方法

```java
public class BookServiceImpl implements BookService, InitializingBean, DisposableBean {
    @Override
    public void destroy() throws Exception {
        System.out.println("service destroy");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("service init");
    }
}
```

配置好service bean对象后直接使用(不需要配init-method 和 destroy-method)

### 详细生命周期

1. 初始化容器

* 创建对象(内存分配)
* 执行构造方法
* 执行属性注入(set操作)
* 执行bean初始化方法

2. 使用bean

* 执行业务操作

3. 关闭/销毁容器

* 执行bean销毁方法

# 四. 依赖注入方式

1. 思考: 像一个类中传递数据的方式有几种?

* 普通方法(set方法)
* 构造方法

2. 思考: 依赖注入描述了在容器中建立了bean与bean之间依赖关系的过程, 如果bean运行需要的是数字或字符串呢?

* 引用类型
* 简单类型(基本类型与String)

3. 依赖注入方式:

- setter注入
    * 简单类型
    * 引用类型
- 构造器注入
    * 简单类型
    * 引用类型

## 1. setter注入

先编写好class类

```java
public class BookDaoImpl implements BookDao{
    private int connectionNum;
    private String databaseName;

    public void setConnectionNum(int connectionNum) {
        this.connectionNum = connectionNum;
    }

    public void setDatabaseName(String databaseName) {
        this.databaseName = databaseName;
    }

    @Override
    public void save() {
        System.out.println("book dao save..." + databaseName + ", " + connectionNum);
    }
}
```

再在配置文件里配置bean

```xml

<bean id="bookDao" class="com.itstudy.dao.impl.BookDaoImpl">
    <property name="databaseName" value="mysql"/>
    <property name="connectionNum" value="10"/>
</bean>
```

最后通过获取容器进行程序运行

```java
import com.itstudy.dao.BookDao;

public class AppForDISet {
  public static void main(String[] args) {
    ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
    BookDao bookDao = (BookDao) ctx.getBean("bookDao");
    bookDao.save();
  }
}
```

运行结果:

```
book dao save...mysql, 10
```

## 2. 构造器注入

编写class类

```java
public class BookServiceImpl implements BookService {

    private BookDao bookDao;

    public BookServiceImpl(BookDao bookDao) {
        this.bookDao = bookDao;
    }

    @Override
    public void save() {
        System.out.println("book service save...");
        bookDao.save();
    }
}
```

配置bean属性, 绑定构造器的使用 constructor-arg 标签, 绑定setter的使用 property 标签

```xml
    <bean id="bookDao" class="com.itstudy.dao.impl.BookDaoImpl"/>

    <bean id="bookService" class="com.itstudy.service.impl.BookServiceImpl">
        <constructor-arg name="bookDao" ref="bookDao"/>
    </bean>
```

ref链接的是 bean id="bookDao", name链接的是 private BookDao bookDao;

简单类型的注入类似, 只展示xml文件

```xml

<bean id="bookDao" class="com.itstudy.dao.impl.BookDaoImpl">
    <constructor-arg name="databaseName" value="mysql"/>
    <constructor-arg name="connectionNum" value="666"/>
</bean>
```

结果如下:

```
book service save...
book dao save...mysql, 666
User Dao save...
```

但是上面的这种写法在类型名称变化时配置文件也要更改bean的name属性, 耦合度大, 所以还有一种解耦方式的写法, 就是利用参数的类型type来配置

```xml

<bean id="bookDao" class="com.itstudy.dao.impl.BookDaoImpl">
    <constructor-arg type="java.lang.String" value="mysql"/>
    <constructor-arg type="int" value="666"/>
</bean>

<bean id="userDao" class="com.itstudy.dao.impl.UserDaoImpl"/>

<bean id="bookService" class="com.itstudy.service.impl.BookServiceImpl">
<constructor-arg type="com.itstudy.dao.BookDao" ref="bookDao"/>
<constructor-arg type="com.itstudy.dao.UserDao" ref="userDao"/>
</bean>
```

但是这种写法还是有弊端, 在多个相同类型的参数下无效, 究极方式就是利用构造器中的参数顺序来辨别

```xml

<bean id="bookDao" class="com.itstudy.dao.impl.BookDaoImpl">
    <constructor-arg index="0" value="666"/>
    <constructor-arg index="1" value="mysql"/>
</bean>

<bean id="userDao" class="com.itstudy.dao.impl.UserDaoImpl"/>

<bean id="bookService" class="com.itstudy.service.impl.BookServiceImpl">
<constructor-arg index="0" ref="bookDao"/>
<constructor-arg index="1" ref="userDao"/>
</bean>
```

以上各种方式运行的结果在简单基础上都一样, 复杂的参数可能只有利用参数顺序的方式才能实现

## 3. 依赖注入方式选择

1. 强制依赖使用构造器进行，使用setter注入有概率不进行注入导致null对象出现
    * 强制依赖指对象在创建的过程中必须要注入指定的参数
2. 可选依赖使用setter注入进行，灵活性强
    * 可选依赖指对象在创建过程中注入的参数可有可无
3. Spring框架倡导使用构造器，第三方框架内部大多数采用构造器注入的形式进行数据初始化，相对严谨
4. 如果有必要可以两者同时使用，使用构造器注入完成强制依赖的注入，使用setter注入完成可选依赖的注入
5. 实际开发过程中还要根据实际情况分析，如果受控对象没有提供setter方法就必须使用构造器注入
6. **"自己开发的模块推荐使用setter注入"**

## 4. 依赖自动装配

IoC容器根据bean所依赖的资源在容器中自动查找并注入到bean中的过程称为自动装配

自动装配的方式

- 按类型(常用)
- 按名称
- 按构造方法
- 不启用自动装配

只需要更改xml配置文件, 改个autowire属性

```xml

<bean id="bookDao" class="com.itstudy.dao.impl.BookDaoImpl"/>

<bean id="bookService" name="service service2 serviceEBI" class="com.itstudy.service.impl.BookServiceImpl"
      autowire="byType"/>
```

按类型装配依赖的那个bean的id都可以不写

**依赖自动装配的特征**

1. 自动装配用于引用类型依赖注入，不能对简单类型进行操作
2. 使用按类型装配时（byType）必须保障容器中相同类型的bean唯一，推荐使用
3. 使用按名称装配时（byName）必须保障容器中具有指定名称的bean，因变量名与配置耦合，不推荐使用
4. 自动装配优先级低于setter注入与构造器注入，同时出现时自动装配配置失效

## 5. 集合注入

class文件编写:

```java
public class BookDaoImpl implements BookDao {
    private int[] array;
    private List<String> list;
    private Set<String> set;
    private Map<String, String> map;
    private Properties properties;

    public void setArray(int[] array) {
        this.array = array;
    }

    public void setList(List<String> list) {
        this.list = list;
    }

    public void setSet(Set<String> set) {
        this.set = set;
    }

    public void setMap(Map<String, String> map) {
        this.map = map;
    }

    public void setProperties(Properties properties) {
        this.properties = properties;
    }

    @Override
    public void save() {
        System.out.println("book dao save...");

        System.out.println("遍历数组" + Arrays.toString(array));
        System.out.println("遍历List" + list);
        System.out.println("遍历Set" + set);
        System.out.println("遍历Map" + map);
        System.out.println("遍历Properties" + properties);
    }
}
```

xml文件配置:

```xml

<bean id="bookDao" class="com.itstudy.dao.impl.BookDaoImpl">
    <property name="array">
        <array>
            <value>100</value>
            <value>200</value>
            <value>300</value>
        </array>
    </property>
    <property name="list">
        <list>
            <value>tom</value>
            <value>alen</value>
            <value>jack</value>
        </list>
    </property>
    <property name="set">
        <set>
            <value>tom</value>
            <value>alen</value>
            <value>jack</value>
            <value>jack</value>
        </set>
    </property>
    <property name="map">
        <map>
            <entry key="tom" value="上海"/>
            <entry key="alen" value="北京"/>
            <entry key="jack" value="广州"/>
        </map>
    </property>
    <property name="properties">
        <props>
            <prop key="country">china</prop>
            <prop key="province">jiangsu</prop>
            <prop key="city">nanjing</prop>
        </props>
    </property>
</bean>
```

运行结果:

```
book dao save...
遍历数组[100, 200, 300]
遍历List[tom, alen, jack]
遍历Set[tom, alen, jack]
遍历Map{tom=上海, alen=北京, jack=广州}
遍历Properties{country=china, province=jiangsu, city=nanjing}
```

# 五. 数据源对象管理(第三方资源配置管理)

## 1. 以阿里巴巴的druid数据源导入为例:

首先导入阿里云的druid数据源, 配置pom.xml

```xml

<dependency>
    <groupId>com.alibaba</groupId>
    <artifactId>druid</artifactId>
    <version>1.2.18</version>
</dependency>
```

编写class文件

```java
public class App {
    public static void main(String[] args) {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
        DataSource dataSource = (DataSource) ctx.getBean("dataSource");
        System.out.println(dataSource);
    }
}
```

配置applicationContext.xml

```xml
<!--管理DruidDataSource对象-->
<bean id="dataSource" class="com.alibaba.druid.pool.DruidDataSource">
    <property name="driverClassName" value="com.mysql.jdbc.Driver"/>
    <property name="url" value="jdbc:mysql://localhost:3306/Mydatabase"/>
    <property name="username" value="root"/>
    <property name="password" value="root"/>
</bean>
```

结果:

```
{
	CreateTime:"2023-06-02 16:34:48",
	ActiveCount:0,
	PoolingCount:0,
	CreateCount:0,
	DestroyCount:0,
	CloseCount:0,
	ConnectCount:0,
	Connections:[
	]
}
```

## 2. 以c3p0数据源导入为例

首先导入c3p0数据源, 配置pom.xml

```xml

<dependency>
    <groupId>c3p0</groupId>
    <artifactId>c3p0</artifactId>
    <version>0.9.1.2</version>
</dependency>
```

编写class文件

```java
public class App {
    public static void main(String[] args) {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
        DataSource dataSource = (DataSource) ctx.getBean("dataSource");
        System.out.println(dataSource);
    }
}
```

配置applicationContext.xml

```xml
<!--c3p0数据源-->
<bean id="dataSource" class="com.mchange.v2.c3p0.ComboPooledDataSource">
    <property name="driverClass" value="com.mysql.jdbc.Driver"/>
    <property name="jdbcUrl" value="jdbc:mysql://localhost:3306/Mydatabase"/>
    <property name="user" value="root"/>
    <property name="password" value="root"/>
</bean>
```

运行前导入下jdbc的坐标

```xml

<dependency>
    <groupId>mysql</groupId>
    <artifactId>mysql-connector-java</artifactId>
    <version>8.0.28</version>
</dependency>

```

结果:

```
6月 02, 2023 4:48:29 下午 com.mchange.v2.log.MLog <clinit>
信息: MLog clients using java 1.4+ standard logging.
6月 02, 2023 4:48:30 下午 com.mchange.v2.c3p0.C3P0Registry banner
信息: Initializing c3p0-0.9.1.2 [built 21-May-2007 15:04:56; debug? true; trace: 10]
6月 02, 2023 4:48:30 下午 com.mchange.v2.c3p0.impl.AbstractPoolBackedDataSource getPoolManager
信息: Initializing c3p0 pool... com.mchange.v2.c3p0.ComboPooledDataSource [ acquireIncrement -> 3, acquireRetryAttempts -> 30, acquireRetryDelay -> 1000, autoCommitOnClose -> false, automaticTestTable -> null, breakAfterAcquireFailure -> false, checkoutTimeout -> 0, connectionCustomizerClassName -> null, connectionTesterClassName -> com.mchange.v2.c3p0.impl.DefaultConnectionTester, dataSourceName -> 1b618bzawy7jrdatws626|59af0466, debugUnreturnedConnectionStackTraces -> false, description -> null, driverClass -> com.mysql.jdbc.Driver, factoryClassLocation -> null, forceIgnoreUnresolvedTransactions -> false, identityToken -> 1b618bzawy7jrdatws626|59af0466, idleConnectionTestPeriod -> 0, initialPoolSize -> 3, jdbcUrl -> jdbc:mysql://localhost:3306/Mydatabase, maxAdministrativeTaskTime -> 0, maxConnectionAge -> 0, maxIdleTime -> 0, maxIdleTimeExcessConnections -> 0, maxPoolSize -> 15, maxStatements -> 0, maxStatementsPerConnection -> 0, minPoolSize -> 3, numHelperThreads -> 3, numThreadsAwaitingCheckoutDefaultUser -> 0, preferredTestQuery -> null, properties -> {password=******, user=******}, propertyCycle -> 0, testConnectionOnCheckin -> false, testConnectionOnCheckout -> false, unreturnedConnectionTimeout -> 0, usesTraditionalReflectiveProxies -> false ]
com.mchange.v2.c3p0.ComboPooledDataSource [ acquireIncrement -> 3, acquireRetryAttempts -> 30, acquireRetryDelay -> 1000, autoCommitOnClose -> false, automaticTestTable -> null, breakAfterAcquireFailure -> false, checkoutTimeout -> 0, connectionCustomizerClassName -> null, connectionTesterClassName -> com.mchange.v2.c3p0.impl.DefaultConnectionTester, dataSourceName -> 1b618bzawy7jrdatws626|59af0466, debugUnreturnedConnectionStackTraces -> false, description -> null, driverClass -> com.mysql.jdbc.Driver, factoryClassLocation -> null, forceIgnoreUnresolvedTransactions -> false, identityToken -> 1b618bzawy7jrdatws626|59af0466, idleConnectionTestPeriod -> 0, initialPoolSize -> 3, jdbcUrl -> jdbc:mysql://localhost:3306/Mydatabase, maxAdministrativeTaskTime -> 0, maxConnectionAge -> 0, maxIdleTime -> 0, maxIdleTimeExcessConnections -> 0, maxPoolSize -> 15, maxStatements -> 0, maxStatementsPerConnection -> 0, minPoolSize -> 3, numHelperThreads -> 3, numThreadsAwaitingCheckoutDefaultUser -> 0, preferredTestQuery -> null, properties -> {password=******, user=******}, propertyCycle -> 0, testConnectionOnCheckin -> false, testConnectionOnCheckout -> false, unreturnedConnectionTimeout -> 0, usesTraditionalReflectiveProxies -> false ]

```

## 3. 加载Properties文件形式开启数据源(对druid源修改)

新建jdbc.Properties文件

```properties
jdbc.driver=com.mysql.jdbc.Driver
jdbc.username=root
jdbc.password=root
jdbc.url=jdbc:mysql://localhost:3306/Mydatabase
```

配置applicationContext.xml文件, 同时要开一个新的spring命名空间context

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xmlns:context="http://www.springframework.org/schema/context"

       xsi:schemaLocation="
       http://www.springframework.org/schema/beans
       http://www.springframework.org/schema/beans/spring-beans.xsd
       http://www.springframework.org/schema/context
       http://www.springframework.org/schema/context/spring-context.xsd">

    <!--加载jdbc配置文件的形式-->
    <!--1, 开启context命名空间-->
    <!--2, 使用context命名空间加载jdbc.properties文件-->
    <!--注意: system-properties-mode="NEVER"表示不加载系统的环境变量-->
    <!--加载多个 location="jdbc.properties, jdbc2.properties" 或者 location="*.properties"-->
    <!--最专业的写法 location="classpath:*.properties"-->
    <!--加载类路径或者jar包里的配置文件写法 location="classpath*:*.properties" 加载块里全部的配置文件-->
    <context:property-placeholder location="classpath*:*.properties" system-properties-mode="NEVER"/>

    <bean id="dataSource" class="com.alibaba.druid.pool.DruidDataSource">
        <property name="driverClassName" value="${jdbc.driver}"/>
        <property name="url" value="${jdbc.url}"/>
        <property name="username" value="${jdbc.username}"/>
        <property name="password" value="${jdbc.password}"/>
    </bean>


</beans>
```

# 六. 容器

## 1. 创建容器

```
//1. 加载类路径下的配置文件
ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
//2. 绝对路径或者相对路径下的配置文件
ApplicationContext ctx2 = new FileSystemXmlApplicationContext("SpringDemo09\\src\\main\\resources\\applicationContext.xml");
  
```

## 2. 获取bean

```
//1, 强转类型获取容器
BookService bookService = (BookService) ctx.getBean("bookService");
//2, 附加文件类型字节码文件获取bean
BookService bookService2 = ctx2.getBean("bookService", BookService.class);
//3, 直接指定文件类型字节码文件获取bean, 局限性在于只能由一个BookService类型的容器
BookService bookService3 = ctx2.getBean(BookService.class);
```

## 3. 容器类层次结构
<img alt="较为直观地描述了容器层次结构的接口继承结构" height="300px" src="容器结构图.png" title="容器类层次结构直观图" width="800px"/>

## 4. BeanFactory
```java
public class AppForBeanFactory {
    public static void main(String[] args) {
        Resource resources = new ClassPathResource("applicationContext.xml");
        BeanFactory bf = new XmlBeanFactory(resources);
        BookDao bookDao = bf.getBean(BookDao.class);
        bookDao.save();
    }
}
```
BeanFactory在初始化的bean时有延迟加载构造器的特性, 而ApplicationContext初始化bean是立即加载构造器,
也可以通过修改bean属性的 lazy-init="true" 来实现延迟加载

# 七. 注解开发
## 1. 注解开发定义bean
使用@Component定义bean, public class上方定义,可以写bean的名称也可以不写
```java
@Component("bookDao")
public class BookDaoImpl implements BookDao{

    @Override
    public void save() {
        System.out.println("book dao save...");
    }
}
@Component
public class BookServiceImpl implements BookService {
    
}
```
如果bean的名称不写的话获取bean不能通过名称获取, 而是需要通过bean的类字节码文件获取
```
BookService bookService = ctx.getBean(BookService.class);
```
核心配置文件中通过组件扫描加载bean, 扫描包里所有的bean, 一般写组织域名就行
```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:context="http://www.springframework.org/schema/context"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xsi:schemaLocation="
       http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd
       http://www.springframework.org/schema/context  http://www.springframework.org/schema/context/spring-context.xsd">
  
    <context:component-scan base-package="com.itstudy"/>
  
</beans>
```
@Component在不同业务可以用别名
- 服务层用@Service
- 数据层用@Repository
- 表现层用@Controller
- 
## 2. 纯注解开发
Spring3.0升级了纯注解开发模式, 使用Java类替代配置文件, 开启了Spring快速开发赛道

新建java类SpringConfig.java ,注解上
- @Configuration 用于设定当前类为配置类
- @ComponentScan("com.itstudy") 用于设定扫描路径, 要写多个包名得用大括号包起来, 数组的形式传进去

如下:
```java
//Configuration代表了配置文件里的
/*
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
        xmlns:context="http://www.springframework.org/schema/context"
        xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
        xsi:schemaLocation="
        http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd
        http://www.springframework.org/schema/context  http://www.springframework.org/schema/context/spring-context.xsd">
</beans>
*/

//ComponentScan("com.itstudy")代表了配置文件里的
//<context:component-scan base-package="com.itstudy"/>

@Configuration
@ComponentScan("com.itstudy")
public class SpringConfig {

}
```
这样可以完全拜托配置文件, 最后在应用层里将将调用方式改为AnnotationConfigApplicationContext(配置类.class)获取

如下:
```java
public class AppForAnnotation {
    public static void main(String[] args) {
        //纯注解文件
        ApplicationContext ctx = new AnnotationConfigApplicationContext(SpringConfig.class);
        BookDao bookDao = (BookDao) ctx.getBean("bookDao");
        System.out.println(bookDao);
        bookDao.save();
        BookService bookService = ctx.getBean(BookService.class);
        System.out.println(bookService);

    }
}
```

## 3. 注解开发bean的作用范围
非单例的注解: 直接在类上注解@Scope("prototype"), 就会生成非单例

```java
@Service
@Scope("prototype")
public class BookServiceImpl implements BookService {
}
```
结果生成对象的地址可以不同了
```
com.itstudy.service.impl.BookServiceImpl@27f981c6
com.itstudy.service.impl.BookServiceImpl@1b11171f
```

## 4. 注解开发bean的生命周期
直接在需要管理生命周期的bean类里, 对自定义的初始化, 销毁方法进行注解, 分别注解上:
- @PostConstruct  构造方法后
- @PreDestroy  彻底销毁前
```java
@Repository("bookDao")
@Scope()
public class BookDaoImpl implements BookDao{
    @Override
    public void save() {
        System.out.println("book dao save...");
    }
    @PostConstruct
    public void init() {
        System.out.println("book dao init...");
    }
    @PreDestroy
    public void destroy() {
        System.out.println("book dao destroy...");
    }
}
```
实现类里调用
```java
public class AppForAnnotation {
  public static void main(String[] args) {
      AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(SpringConfig.class);
      BookDao bookDao = (BookDao) ctx.getBean("bookDao");
      System.out.println(bookDao);
      bookDao.save();
      ctx.registerShutdownHook();
  }
}
```
运行结果
```
book dao init...
com.itstudy.dao.impl.BookDaoImpl@70e9c95d
book dao save...
book dao destroy...
```
## 5. 注解开发bean的注入依赖
### (1).复杂属性注解自动装配:

直接在需要调用的成员上注解@Autowired, 即可自动注入, 本质上是通过暴力反射对应属性来为私有属性初始化数据, 所以在类里可以不写set方法,
自动装配需要无参构造方法, 而且没有给调用的bean起名默认是按类型来自动装配的
```java
@Service
public class BookServiceImpl implements BookService {
  //5, 删除业务层中创建的对象
  @Autowired
  private BookDao bookDao;
  @Override
  public void save() {
    System.out.println("book service save...");
    bookDao.save();
  }
}
```
运行结果:
```
com.itstudy.service.impl.BookServiceImpl@e350b40
book service save...
book dao save...
```
如果有多个相同类型的对象, 默认按类型就失效了, 需要按名字来自动装配, 得给bean得类起名
@Repository("BookDao")
```java
@Repository("bookDao")
public class BookDaoImpl implements BookDao{
}

@Repository("bookDao2")
public class BookDaoImpl2 implements BookDao{
}
```
服务层里调用时根据你写的成员名字: "private BookDao bookDao2;" 来调用BookDaoImpl2类

当然这种需要自己根据注解名字来确定成员的方式过于麻烦, 也可以通过加上@Qualifier("bookDao2")来绑定对应的类, 
服务层里的成员名就可以按自己的想法来起名
```java
@Service
public class BookServiceImpl implements BookService {
    //5, 删除业务层中创建的对象
    @Autowired
    @Qualifier("bookDao2")
    private BookDao bookDao;
    @Override
    public void save() {
        System.out.println("book service save...");
        bookDao.save();
    }

}
```
结果是BookDaoImpl2里的save方法调用
```
com.itstudy.service.impl.BookServiceImpl@6c1a5b54
book service save...
book dao save...2
```
注意: @Qualifier("bookDao2")必须依赖@Autowired来使用

### (2). 简单属性注解自动装配
直接在简单属性成员变量上注解@value("itstudy6666"), 并提供对应的值
```    
@Value("itstudy6666")
private String name;
```
这样注解提供对应的值的优势在于, 可以通过配置文件来给它赋值, 也就可以从外部提供对应的值

配置文件value.properties
```properties
name=itstudy666
```
然后在配置类SpringConfig.class里加上注解@PropertySource("value.properties")
```java
@Configuration
@ComponentScan("com.itstudy")
@PropertySource("value.properties")
public class SpringConfig {
}
```
最后将@value()括号里加上@value("${name}"), 运行结果一样, 
与xml文件里配置不同, 不支持通配符*.properties, 但可以加上classpath:
```
@PropertySource("class:value.properties")
```
## 6. 注解开发bean管理第三方bean
### (1). 第三方bean管理
依然导包druid, 详细请看往期学习内容

然后在SpringConfig里配置获取第三方bean的方法, 因为我们无法在第三包里给它写上名称, 只能把它获取出来再命名
注解方法返回的是一个bean, 可以写名也可以不写
```java
@Configuration
public class SpringConfig {

    //1, 定义一个方法获得要管理的bean
    //2, 添加@Bean表示返回的是一个Bean
    @Bean
    public DataSource dataSource() {
        DruidDataSource ds = new DruidDataSource();
        ds.setDriverClassName("com.mysql.jdbc.Driver");
        ds.setUrl("jdbc:mysql://localhost:3306/spring_db");
        ds.setUsername("root");
        ds.setPassword("root");
        return ds;
    }
}
```
然后实现层app里进行调用
```java
public class App {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(SpringConfig.class);
        DataSource dataSource = ctx.getBean(DataSource.class);
        System.out.println(dataSource);

    }
}
```
结果如下
```
{
	CreateTime:"2023-06-03 16:00:30",
	ActiveCount:0,
	PoolingCount:0,
	CreateCount:0,
	DestroyCount:0,
	CloseCount:0,
	ConnectCount:0,
	Connections:[
	]
}
```
对于这种第三方bean获取方法建议不要写在一个SpringConfig配置文件里, 写在自己单独的配置文件里如:JdbcConfig配置文件
```java
@Configuration
public class JdbcConfig {
    //获取bean的方法, 如上druid的获取
}
```
只需要在SpringConfig配置文件里注解导入下JdbcConfig配置文件即可, 结果依然可以实现
```java
@Configuration
//@ComponentScan("com.itstudy.config") 不推荐
@Import({JdbcConfig.class}) //推荐, 可以清楚看到导入了哪些配置文件
public class SpringConfig {
}
```
### (2). 第三方bean依赖注入
简单类型: 
```java
@Configuration
public class JdbcConfig {
  @Value("com.mysql.jdbc.Driver")
  private String driver;

  @Value("jdbc:mysql://localhost:3306/spring_db")
  private String url;

  @Value("root")
  private String username;

  @Value("root")
  private String password;


  //1, 定义一个方法获得要管理的bean
  //2, 添加@Bean表示返回的是一个Bean
  @Bean
  public DataSource dataSource() {
    DruidDataSource ds = new DruidDataSource();
    ds.setDriverClassName(driver);
    ds.setUrl(url);
    ds.setUsername(username);
    ds.setPassword(password);
    return ds;
  }
}
```
运行结果和上面一样

复杂类型(注入方式非常特殊):
- 首先声明注解下bean的类文件
```java
@Repository
public class BookDaoImpl implements BookDao {
}
```
- 然后SpringConfig里注解扫描要注入的类文件BookDaoImpl
```java
@Configuration
@ComponentScan("com.itstudy")
@Import({JdbcConfig.class})
public class SpringConfig {
}
```
- 最后在Jdbc配置文件的获取bean方法里添加下BookDao类的形参, Spring底层会自动执行自动装配
```
@Bean
public DataSource dataSource(BookDao bookDao) {
        System.out.println(bookDao);
        .....
}
```
结果打印出bookDao的对象地址
```
com.itstudy.dao.impl.BookDaoImpl@69e153c5
```
引用类型注入只要为获取bean定义方法设置形参即可, 容器会根据类型自动装配

## 7. XML配置对比注解配置
<img height="400px" src="xml对比注解配置.png" width="800px"/>

图片来自B站黑马程序员的[Spring-25-注解开发总结](https://www.bilibili.com/video/BV1Fi4y1S7ix?p=27&spm_id_from=pageDriver&vd_source=b1a441fcb369fd950d8bf49580ca3248 
"https://www.bilibili.com/video/BV1Fi4y1S7ix?p=27&spm_id_from=pageDriver&vd_source=b1a441fcb369fd950d8bf49580ca3248") 


___

