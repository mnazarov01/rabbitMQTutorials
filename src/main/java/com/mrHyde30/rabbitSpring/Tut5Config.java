package com.mrHyde30.rabbitSpring;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;

@Profile({"tut5", "topics"})
@Configuration
public class Tut5Config {

    @Bean
    public TopicExchange topic() {
        return new TopicExchange("tut.topic");
    }

    @Profile("receiver")
    private static class ReceiverConfig {

        @Bean
        public Tut5Receiver receiver() {
            return new Tut5Receiver();
        }

        @Bean
        public Queue anonymousQueue1() {
            return new AnonymousQueue();
        }

        @Bean
        public Queue anonymousQueue2() {
            return new AnonymousQueue();
        }

        @Bean
        public Binding binding1a(TopicExchange topic, Queue anonymousQueue1) {
            return BindingBuilder.bind(anonymousQueue1).to(topic).with("*.orange.*");
        }

        @Bean
        public Binding binding1b(TopicExchange topic, Queue anonymousQueue1) {
            return BindingBuilder.bind(anonymousQueue1).to(topic).with("*.*.rabbit");
        }

        @Bean
        public Binding binding2a(TopicExchange topic, Queue anonymousQueue2) {
            return BindingBuilder.bind(anonymousQueue2).to(topic).with("lazy.#");
        }

    }

    @Profile("sender")
    @Bean
    public Tut5Sender sender() {
        return new Tut5Sender();
    }

}
