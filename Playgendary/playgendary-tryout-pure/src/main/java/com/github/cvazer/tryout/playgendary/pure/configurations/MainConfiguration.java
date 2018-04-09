package com.github.cvazer.tryout.playgendary.pure.configurations;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "com.github.cvazer.tryout.playgendary.pure.controllers")
public class MainConfiguration {

}
