package com.example.demo.shandian.domain;

import lombok.Data;

import static com.example.demo.shandian.inter.Command.LOGIN_REQUEST;

/**
 * @Classname LoginRequestPacket
 * @Description TODO
 * @Date 2022/10/18 14:52
 * @Created by 61635
 */
@Data
public class LoginRequestPacket extends Packet{

    private String userId;
    private String username;
    private String password;

    @Override
    public Byte getCommand() {
        return LOGIN_REQUEST;
    }
}
