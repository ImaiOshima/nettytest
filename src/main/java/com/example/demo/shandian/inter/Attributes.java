package com.example.demo.shandian.inter;

import io.netty.util.AttributeKey;

/**
 * @Classname Attributes
 * @Description TODO
 * @Author Imai
 * @Date 2022/10/18 22:28
 * @Created by 61635
 */
public interface Attributes {
    AttributeKey<Boolean> LOGIN = AttributeKey.newInstance("login");
}
