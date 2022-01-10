package com.example.demo.netty;

import jdk.nashorn.internal.ir.RuntimeNode;

import javax.security.auth.Subject;
import java.sql.ResultSet;

/**
 * @Classname SubThread
 * @Description TODO
 * @Date 2022/1/10 23:09
 * @Created by 61635
 */
public class SubThread extends Thread {
    private RequestFuture request;
    public SubThread(RequestFuture request){
        this.request = request;
    }
    @Override
    public void run(){
        Response resp = new Response();
        resp.setId(request.getId());
        resp.setResult("server response:" + Thread.currentThread().getId());
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        RequestFuture.received(resp);
    }
}
