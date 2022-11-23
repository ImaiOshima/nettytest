package com.example.demo.shandian.domain;

import lombok.Data;

/**
 * @Classname Packet
 * @Description TODO
 * @Date 2022/10/18 14:50
 * @Created by 61635
 */
@Data
public abstract class Packet {
    private Byte version = 1;
    public abstract Byte getCommand();
}
