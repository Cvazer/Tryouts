package com.github.cvazer.tryout.playgendary.pure.configurations;

import com.github.cvazer.tryout.playgendary.pure.security.RestAuthenticationProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
@ComponentScan("com.github.cvazer.tryout.playgendary.pure.security")
public class RestSecurityConfiguration extends WebSecurityConfigurerAdapter {

    private final RestAuthenticationProvider provider;

    @Autowired
    public RestSecurityConfiguration(RestAuthenticationProvider provider) {
        this.provider = provider;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
//        http
//                .authorizeRequests()
//                    .antMatchers("/login").permitAll()
//                    .antMatchers("/**").hasRole("USER")
//                    .anyRequest().authenticated()
//                    .and()
//                .formLogin()
//                    .loginPage("/login")
//                    .permitAll()
//                    .and()
//                .httpBasic();
//        http
//                .authorizeRequests()
//                    .antMatchers("/login").permitAll()
//                    .antMatchers("/**").hasRole("USER");
        http
                .authorizeRequests().anyRequest().authenticated()
                    .and()
                .httpBasic();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(provider);
    }
}
