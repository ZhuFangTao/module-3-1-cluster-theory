package com.lagou.nettyrpc.server.coder;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.util.List;

/**
 * \* User: ZhuFangTao
 * \* Date: 2020/6/17 2:40 下午
 * \
 */
public class RpcDecoder<T> extends ByteToMessageDecoder {

    private Class clazz;
    private Serializer serializer;

    public RpcDecoder(Class<T> clazz, Serializer serializer) {
        this.clazz = clazz;
        this.serializer = serializer;
    }

    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
        if (clazz != null) {
            if (in.readableBytes() < 4) { //首先读取 int类型的长度 byteBuf.writeInt(bytes.length);
                return;
            }
            in.markReaderIndex();
            //读取消息长度
            int msgLength = in.readInt();
            //做readindex
            int length = in.readableBytes();
            if (length < msgLength) {
                in.resetReaderIndex();
                return;
            }
            byte[] bytes = new byte[msgLength];
            in.readBytes(bytes);

            //反序列化对象
            Object object = serializer.deserialize(clazz, bytes);
            out.add(object);
        }
    }
}