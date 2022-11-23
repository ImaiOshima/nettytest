package com.example.demo.shandian.utils;

import com.alibaba.fastjson.JSON;
import com.example.demo.shandian.inter.Serializer;
import com.example.demo.shandian.inter.SerializerAlgorithm;

/**
 * @Classname JSONSerialzier
 * @Description TODO
 * @Author Imai
 * @Date 2022/10/18 15:03
 * @Created by 61635
 */
public class JSONSerializer implements Serializer {
    @Override
    public Byte getSerializerAlgorithm() {
        return SerializerAlgorithm.JSON;
    }

    @Override
    public byte[] serializer(Object object) {
        return JSON.toJSONBytes(object);
    }

    @Override
    public <T> T deserialize(Class<T> clazz, byte[] bytes) {
        return JSON.parseObject(bytes,clazz);
    }
}
