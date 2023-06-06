package com.itstudy.config;

import org.springframework.context.annotation.*;

@Configuration
@ComponentScan("com.itstudy")
@PropertySource({"classpath:jdbc.properties"})
@Import({JdbcConfig.class, MyBatisConfig.class})
@EnableAspectJAutoProxy
public class SpringConfig {

}
