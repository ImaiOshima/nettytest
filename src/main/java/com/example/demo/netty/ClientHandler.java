package com.example.demo.netty;

import com.alibaba.fastjson.JSONObject;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.concurrent.Promise;
import lombok.Data;

/**
 * @Classname ClientHandler
 * @Description TODO
 * @Date 2022/1/12 0:14
 * @Created by 61635
 */
@Data
public class ClientHandler extends ChannelInboundHandlerAdapter {
    private Promise<Response> promise;
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        Response response = JSONObject.parseObject(msg.toString(),Response.class);
        promise.setSuccess(response);
    }
}
