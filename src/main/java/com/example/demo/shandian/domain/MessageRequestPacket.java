package com.example.demo.shandian.domain;

import static com.example.demo.shandian.inter.Command.MESSAGE_REQUEST;

import lombok.Data;

/**
 * @Classname MessageRequestPacket
 * @Description TODO
 * @Author Imai
 * @Date 2022/10/18 22:13
 * @Created by 61635
 */
@Data
public class MessageRequestPacket extends Packet{
    private String message;
    @Override
    public Byte getCommand() {
        return MESSAGE_REQUEST;
    }
}
