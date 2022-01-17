package com.example.demo.netty;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import lombok.Data;

@Data
public class RequestFuture {
    public static Map<Long,RequestFuture> futures = new ConcurrentHashMap<>();

    private long id;
    private Object request;
    private Object result;
    private long timeout=5000;
    private String path;

    public static void addFuture(RequestFuture future){
        futures.put(future.getId(),future);
    }

    public Object get(){
        synchronized (this){
            while(this.result == null){
                try {
                    this.wait(timeout);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
        return this.result;
    }

    public static void received(Response resp){
        RequestFuture future = futures.remove(resp.getId());
        if(future!=null){
            future.setResult(resp.getResult());
        }
        synchronized (future){
            future.notify();
        }
    }
}
