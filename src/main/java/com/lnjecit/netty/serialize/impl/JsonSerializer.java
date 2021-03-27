package com.lnjecit.netty.serialize.impl;

import com.alibaba.fastjson.JSON;
import com.lnjecit.netty.serialize.Serializer;
import com.lnjecit.netty.serialize.SerializerAlgorithm;

public class JsonSerializer implements Serializer {
    @Override
    public byte getSerializerAlgorithm() {
        return SerializerAlgorithm.JSON;
    }

    @Override
    public byte[] serialize(Object object) {
        return JSON.toJSONBytes(object);
    }

    @Override
    public <T> T deserialize(Class<T> clazz, byte[] bytes) {
        return JSON.parseObject(bytes, clazz);
    }
}
