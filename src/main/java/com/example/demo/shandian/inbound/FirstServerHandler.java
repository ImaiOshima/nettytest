package com.example.demo.shandian.inbound;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Date;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 * @Classname FirstServerHandler
 * @Description TODO
 * @Date 2022/10/18 13:00
 * @Created by 61635
 */
public class FirstServerHandler extends ChannelInboundHandlerAdapter {
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        //接受数据
        ByteBuf byteBuf = (ByteBuf) msg;
        System.out.println(new Date() + "：服务端读到数据 ->"
                + byteBuf.toString(Charset.forName("utf-8")));
        //返回数据给客户端
        System.out.println(new Date() + ": 服务端写出数据" );
        ByteBuf out = getByteBuf(ctx);
        ctx.channel().writeAndFlush(out);

    }

    private ByteBuf getByteBuf(ChannelHandlerContext ctx){
        byte[] bytes = "你好，欢迎关注我的微信公众号，《闪电侠的博客》！".getBytes(StandardCharsets.UTF_8);

        ByteBuf buf = ctx.alloc().buffer();

        buf.writeBytes(bytes);
        return buf;
    }
}
