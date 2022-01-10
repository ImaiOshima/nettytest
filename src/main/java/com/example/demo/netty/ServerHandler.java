package com.example.demo.netty;

import com.sun.javafx.image.ByteToBytePixelConverter;
import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandler;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.nio.charset.Charset;

@ChannelHandler.Sharable
public class ServerHandler extends ChannelInboundHandlerAdapter {
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
       if(msg instanceof ByteBuf){
           System.out.println(((ByteBuf)msg).toString(Charset.defaultCharset()));
       }
       ctx.channel().writeAndFlush("msg has received!");

    }
}
