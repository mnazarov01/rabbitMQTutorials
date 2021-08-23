package com.mrHyde30.rabbitSpring;


import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.concurrent.atomic.AtomicInteger;

public class Tut2Sender {

    @Autowired
    public Queue queue;

    @Autowired
    public RabbitTemplate template;

    private final AtomicInteger dots = new AtomicInteger();
    private final AtomicInteger count = new AtomicInteger();

    @Scheduled(fixedDelay = 1000, initialDelay = 500)
    public void send() {

        StringBuilder stringBuilder = new StringBuilder();

        if (dots.incrementAndGet() == 4) {
            dots.set(1);
        }

        stringBuilder.append(".".repeat(dots.get()));
        stringBuilder.append(count.incrementAndGet());

        String message = stringBuilder.toString();
        template.convertAndSend(queue.getName(), message);
        System.out.println(" [x] Sent '" + message + "'");

    }

}
