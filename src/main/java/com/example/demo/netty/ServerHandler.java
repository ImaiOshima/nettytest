package com.example.demo.netty;

import com.alibaba.fastjson.JSONObject;

import com.example.demo.domain.Mediator;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

@ChannelHandler.Sharable
public class ServerHandler extends ChannelInboundHandlerAdapter {
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        RequestFuture request = JSONObject.parseObject(msg.toString(),RequestFuture.class);
        Response response = Mediator.process(request);
        ctx.channel().writeAndFlush(JSONObject.toJSONString(response));
    }
}
