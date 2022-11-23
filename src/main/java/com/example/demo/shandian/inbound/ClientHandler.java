package com.example.demo.shandian.inbound;

import java.util.Date;
import java.util.UUID;

import com.example.demo.shandian.domain.LoginRequestPacket;
import com.example.demo.shandian.domain.LoginResponsePacket;
import com.example.demo.shandian.domain.MessageResponsePacket;
import com.example.demo.shandian.domain.Packet;
import com.example.demo.shandian.utils.LoginUtil;
import com.example.demo.shandian.utils.PacketCodeC;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 * @Classname ClientHandler
 * @Description TODO
 * @Author Imai
 * @Date 2022/10/18 15:42
 * @Created by 61635
 */
public class ClientHandler extends ChannelInboundHandlerAdapter {
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println(new Date() + ": 客户端开始登录");

        LoginRequestPacket loginRequestPacket = new LoginRequestPacket();
        loginRequestPacket.setUserId(UUID.randomUUID().toString());
        loginRequestPacket.setUsername("flash");
        loginRequestPacket.setPassword("pwd");

        ByteBuf buffer = PacketCodeC.INSTANCE.encode(ctx.alloc(),loginRequestPacket);
        ctx.channel().writeAndFlush(buffer);
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        System.out.println(new Date() + " :客户端接受到数据!");
        ByteBuf buf = (ByteBuf) msg;

        Packet packet = PacketCodeC.INSTANCE.decode(buf);
        if(packet instanceof LoginResponsePacket){
            LoginResponsePacket loginResponsePacket = (LoginResponsePacket)packet;

            if (loginResponsePacket.isSuccess()) {
                LoginUtil.markLogin(ctx.channel());
                System.out.println(new Date() + ":客户端登录成功");
            }else{
                System.out.println(new Date() + ":客户端登录失败，原因：" + loginResponsePacket.getReason());
            }
        }else if(packet instanceof MessageResponsePacket){
            MessageResponsePacket messageResponsePacket = (MessageResponsePacket) packet;
            System.out.println(new Date() + " :收到服务端的消息:" + messageResponsePacket.getMessage());
        }
    }
}
