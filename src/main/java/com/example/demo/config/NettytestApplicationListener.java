package com.example.demo.config;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;

import com.example.demo.netty.NettyService;

/**
 * @Classname NettytestApplicationListener
 * @Description TODO
 * @Date 2022/1/16 13:42
 * @Created by 61635
 */
public class NettytestApplicationListener implements ApplicationListener<ContextRefreshedEvent> {

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        new Thread(
            ()->{
                NettyService.start();
            }).start();
    }
}
