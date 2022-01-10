package com.example.demo.netty;

import java.util.ArrayList;
import java.util.List;

public class FutureMain {
    //这段代码的原理：
    // 主线程运行完之前是 调用get方法 使的所有get进入到了wait(timeout)之中
    // 也因为synchronized锁的是对象的对象头
    // subThrad.start() 然后进行运行 run()中创建完resp 调用received() 唤醒
    // 这个唤醒也是有个小细节 就是wait 是释放锁的 会吧线程放入到等待队列中 进而使得notify来唤醒
    public static void main(String[] args) {
        List<RequestFuture> reqs = new ArrayList<>();
        for(int i = 0;i<100;i++){
            long id = i;
            RequestFuture req = new RequestFuture();
            req.setId(id);
            req.setRequest("Hello World");
            RequestFuture.addFuture(req);
            reqs.add(req);
            sendMsg(req);
            SubThread subThread = new SubThread(req);
            subThread.start();
        }

        for(RequestFuture req: reqs){
            Object result = req.get();
            System.out.println(result.toString());
        }
    }

    public static void sendMsg(RequestFuture req){
        System.out.println("客户端发送数据，请求id为===========" + req.getId());
    }
}
