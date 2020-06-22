package com.lagou.nettyrpc.server.coder;

import java.io.IOException;

/**
 * \* @Author: ZhuFangTao
 * \* @Date: 2020/6/17 2:43 下午
 * \
 */
public interface Serializer {

    /**
     * java对象转换为二进制
     *
     * @param object
     * @return
     */

    byte[] serialize(Object object) throws IOException;


    /**
     * 二进制转换成java对象
     *
     * @param clazz
     * @param msg
     * @param <T>
     * @return
     */

    <T> T deserialize(Class<T> clazz, byte[] msg) throws IOException;

}