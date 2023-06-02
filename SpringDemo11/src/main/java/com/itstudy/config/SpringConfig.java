package com.itstudy.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@ComponentScan("com.itstudy")
@PropertySource({"classpath:value.properties"})
public class SpringConfig {

}
