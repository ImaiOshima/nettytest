package com.example.demo.shandian.client;

import com.example.demo.shandian.domain.MessageRequestPacket;
import com.example.demo.shandian.inbound.ClientHandler;

import com.example.demo.shandian.utils.LoginUtil;
import com.example.demo.shandian.utils.PacketCodeC;
import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.util.Scanner;

/**
 * @Classname NettyClient
 * @Description TODO
 * @Date 2022/10/17 2:12
 * @Created by 61635
 */
public class NettyClient {
    public static void main(String[] args) throws InterruptedException {
        Bootstrap bootstrap = new Bootstrap();
        NioEventLoopGroup group = new NioEventLoopGroup();

        bootstrap.group(group).channel(NioSocketChannel.class)
                .handler(new ChannelInitializer<Channel>() {
                    @Override
                    protected void initChannel(Channel ch) throws Exception {
                        ch.pipeline().addLast(new ClientHandler());
                    }
                });
        connect(bootstrap,"127.0.0.1",8000,5);
    }

    public static void connect(Bootstrap bootstrap,String host,int port,int retry){
        bootstrap.connect(host,port).addListener(future -> {
            if(future.isSuccess()){
                Channel channel =((ChannelFuture)future).channel();
                startConsoleThread(channel);
            }
        });
    }

    public static void startConsoleThread(Channel channel){
        new Thread(()->{
            while(!Thread.interrupted()){
                if(LoginUtil.hasLogin(channel)){
                    System.out.println("输入消息发送至服务端：");
                    Scanner sc = new Scanner(System.in);
                    String line = sc.nextLine();

                    MessageRequestPacket packet = new MessageRequestPacket();
                    packet.setMessage(line);
                    ByteBuf buf = PacketCodeC.INSTANCE.encode(channel.alloc(),packet);
                    channel.writeAndFlush(packet);
                }
            }
        }).start();
    }
}
