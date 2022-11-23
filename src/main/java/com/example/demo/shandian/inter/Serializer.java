package com.example.demo.shandian.inter;

import com.example.demo.shandian.utils.JSONSerializer;

/**
 * @Classname Serializer
 * @Description TODO
 * @Date 2022/10/18 14:57
 * @Created by 61635
 */
public interface Serializer {
    Byte getSerializerAlgorithm();

    byte[] serializer(Object object);
    <T> T deserialize(Class<T> clazz,byte[] bytes);

    byte JSON_SERIALIZER = 1;
    Serializer DEFAULT = new JSONSerializer();
}
