package com.atyume.config;

import org.springframework.context.annotation.Configuration;
import tk.mybatis.spring.annotation.MapperScan;

@Configuration
@MapperScan(basePackages = "com.atyume.modules.*.mapper")
public class MyBatisConfig {

}
