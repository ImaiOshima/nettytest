package com.example.demo.shandian.domain;

import static com.example.demo.shandian.inter.Command.LOGIN_RESPONSE;

import lombok.Data;

/**
 * @Classname LoginRequestPacket
 * @Description TODO
 * @Date 2022/10/18 14:52
 * @Created by 61635
 */
@Data
public class LoginResponsePacket extends Packet{

    private boolean success;
    private String reason;

    @Override
    public Byte getCommand() {
        return LOGIN_RESPONSE;
    }
}
