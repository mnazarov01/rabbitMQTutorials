package com.mrHyde30.rabbitSpring;

import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.concurrent.atomic.AtomicInteger;

public class Tut3Sender {

    @Autowired
    public RabbitTemplate template;

    @Autowired
    public FanoutExchange exchange;

    private AtomicInteger dots = new AtomicInteger();
    private AtomicInteger count = new AtomicInteger();

    @Scheduled(fixedDelay = 1000, initialDelay = 500)
    public void send(){
        StringBuilder builder = new StringBuilder("Hello");
        if (dots.getAndIncrement() == 3) {
            dots.set(1);
        }
        builder.append(".".repeat(Math.max(0, dots.get())));
        builder.append(count.incrementAndGet());

        String message = builder.toString();
        template.convertAndSend(exchange.getName(), "", message);
        System.out.println(" [x] Sent '" + message + "'");
    }


}
