package com.mrHyde30.rabbitSpring;


import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.util.StopWatch;

@RabbitListener(queues = "hello")
public class Tut2Receiver {

    private final int instance;

    public Tut2Receiver(int instance) {
        this.instance = instance;
        System.out.println("receiver created");
    }

    @RabbitHandler
    public void receive(String message) throws InterruptedException {

        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        System.out.println("instance " + this.instance +
                " [x] Received '" + message + "'");
        doWork(message);
        stopWatch.stop();
        System.out.println("instance " + this.instance +
                " [x] Done in " + stopWatch.getTotalTimeSeconds() + "s");


    }

    private void doWork(String in) throws InterruptedException {
        for (char ch : in.toCharArray()) {
            if (ch == '.') {
                Thread.sleep(1000);
            }
        }
    }


}
