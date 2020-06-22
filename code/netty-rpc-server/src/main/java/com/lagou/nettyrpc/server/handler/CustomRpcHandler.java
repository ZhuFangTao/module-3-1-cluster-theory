package com.lagou.nettyrpc.server.handler;

import com.lagou.nettyrpc.server.pojo.RpcRequest;
import com.lagou.nettyrpc.server.util.BeanUtil;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.lang.reflect.Method;

/**
 * \* User: ZhuFangTao
 * \* Date: 2020/6/17 10:43 上午
 * \
 */
public class CustomRpcHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        RpcRequest request = (RpcRequest) msg;
        Object bean = BeanUtil.getBeanByType(request.getClassName());
        Method method = bean.getClass().getMethod(request.getMethodName(), request.getParameterTypes());
        Object result = method.invoke(bean, request.getParameters());
        ctx.writeAndFlush(result);
    }
}