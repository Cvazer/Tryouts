package com.github.cvazer.beatdev.tryout.configurations;

import com.github.cvazer.beatdev.tryout.misc.UserStatusToken;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import java.util.function.Supplier;

@Configuration
public class ModelBeansConfiguration {

    public @Bean Supplier<UserStatusToken> statusTokenSupplier() { return this::userStatusToken; }
    public @Bean @Scope("prototype") UserStatusToken userStatusToken(){ return new UserStatusToken(); }

}
