package com.example.demo.config;

import java.lang.reflect.Method;
import java.util.Map;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Controller;

import com.example.demo.annotation.Remote;
import com.example.demo.domain.Mediator;

/**
 * @author 61635
 * @Classname InitLoadRemoteMethod
 * @Description TODO
 * @Date 2022/1/16 13:54
 * @Created by 61635
 */
public class InitLoadRemoteMethod implements ApplicationListener<ContextRefreshedEvent>, Ordered {

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        Map<String,Object> controllerBeans = contextRefreshedEvent.getApplicationContext().getBeansWithAnnotation(Controller.class);
        for(String key:controllerBeans.keySet()){
            Object bean = controllerBeans.get(key);
            Method[] methods = bean.getClass().getDeclaredMethods();
            for(Method method:methods){
                if(method.isAnnotationPresent(Remote.class)){
                    Remote remote = method.getAnnotation(Remote.class);
                    String methodVal = remote.value();
                    Mediator.MethodBean methodBean = new Mediator.MethodBean();
                    methodBean.setBean(bean);
                    methodBean.setMethod(method);
                    Mediator.methodBeans.put(methodVal,methodBean);
                }
            }
        }
    }

    @Override
    public int getOrder() {
        return -1;
    }
}
