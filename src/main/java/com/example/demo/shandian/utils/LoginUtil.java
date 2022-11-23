package com.example.demo.shandian.utils;

import com.example.demo.shandian.inter.Attributes;

import io.netty.channel.Channel;
import io.netty.util.Attribute;

/**
 * @Classname LoginUtil
 * @Description TODO
 * @Author Imai
 * @Date 2022/10/18 22:29
 * @Created by 61635
 */
public class LoginUtil {
    public static void markLogin(Channel channel){
        channel.attr(Attributes.LOGIN).set(true);
    }

    public static boolean hasLogin(Channel channel){
        Attribute<Boolean> loginAttr = channel.attr(Attributes.LOGIN);
        return loginAttr.get() != null;
    }
}
