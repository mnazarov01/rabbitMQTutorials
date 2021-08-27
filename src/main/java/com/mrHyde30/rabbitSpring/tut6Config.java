package com.mrHyde30.rabbitSpring;

import org.springframework.amqp.core.DirectExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Profile("tut6")
@Configuration
public class tut6Config {

    @Profile("server")
    public static class ClientConfig{

        @Bean
        public DirectExchange direct() {
            return new DirectExchange("tut.rpc");
        }



    }

    @Profile("client")
    public static class ServerConfig{

    }

}
