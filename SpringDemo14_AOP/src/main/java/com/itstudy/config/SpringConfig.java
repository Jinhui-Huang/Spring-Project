package com.itstudy.config;


import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Configuration
@ComponentScan("com.itstudy")
@EnableAspectJAutoProxy
public class SpringConfig {

}
