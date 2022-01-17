package com.example.demo.netty;

import java.nio.charset.Charset;

import com.alibaba.fastjson.JSONObject;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.PooledByteBufAllocator;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.handler.codec.LengthFieldPrepender;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;

/**
 * @author 61635
 * @Classname NettyClient
 * @Description TODO
 * @Date 2022/1/12 0:09
 * @Created by 61635
 */
public class NettyClient {
    public static EventLoopGroup group = null;
    public static Bootstrap bootstrap = null;
    public static ChannelFuture future = null;
    static{
        bootstrap = new Bootstrap();
        group = new NioEventLoopGroup();
        bootstrap.channel(NioSocketChannel.class);
        bootstrap.group(group);
        bootstrap.option(ChannelOption.ALLOCATOR, PooledByteBufAllocator.DEFAULT);
        final ClientHandler handler = new ClientHandler();
        bootstrap.handler(new ChannelInitializer<NioSocketChannel>() {
            @Override
            protected void initChannel(NioSocketChannel ch) throws Exception {
                ch.pipeline().addLast(new LengthFieldBasedFrameDecoder(Integer.MAX_VALUE,0,4,0,4));
                ch.pipeline().addLast(new StringDecoder());
                ch.pipeline().addLast(handler);
                ch.pipeline().addLast(new LengthFieldPrepender(4,false));
                ch.pipeline().addLast(new StringEncoder(Charset.forName("UTF-8")));
            }
        });
        try {
            ChannelFuture future = bootstrap.connect("127.0.0.1",8080).sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        NettyClient client = new NettyClient();
        for(int i = 0;i<100;i++){
            Object result = client.sendRequest("hello");
            System.out.println(result);
        }
    }

    public Object sendRequest(Object msg){
        try{
            RequestFuture request = new RequestFuture();
            request.setRequest(msg);
            String requestStr = JSONObject.toJSONString(request);
            future.channel().writeAndFlush(requestStr);
            Object result = request.get();
            return result;
        }catch (Exception e){
            e.printStackTrace();
            throw e;
        }
    }
}
