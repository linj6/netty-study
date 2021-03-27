package com.lnjecit.netty.serialize;

import com.lnjecit.netty.serialize.impl.JsonSerializer;

public interface Serializer {

    Serializer DEFAULT = new JsonSerializer();

    /**
     * 序列化算法
     */
    byte getSerializerAlgorithm();

    /**
     * java对象转换成二进制
     */
    byte[] serialize(Object object);

    /**
     * 二进制转换为java对象
     */
    <T> T deserialize(Class<T> clazz, byte[] bytes);
}
