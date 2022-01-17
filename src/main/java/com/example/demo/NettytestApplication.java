package com.example.demo;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;


public class NettytestApplication {

    private static volatile boolean running = true;

    public static void main(String[] args) {
        try {
            AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext("com.example.demo");
            Runtime.getRuntime().addShutdownHook(new Thread() {
                @Override
                public void run() {
                    try {
                        context.stop();
                    } catch (Exception e) {
                    }
                    synchronized (NettytestApplication.class) {
                        running = false;
                        NettytestApplication.class.notify();
                    }
                }
            });
            context.start();
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(1);
        }
        System.out.println("服务器已启动====");
        synchronized (NettytestApplication.class) {
            while (running) {
                try {
                    NettytestApplication.class.wait();
                } catch (Throwable e) {
                }
            }
        }
    }
}
