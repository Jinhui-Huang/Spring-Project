package com.itstudy.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

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
