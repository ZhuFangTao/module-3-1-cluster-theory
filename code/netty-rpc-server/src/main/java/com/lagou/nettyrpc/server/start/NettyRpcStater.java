package com.lagou.nettyrpc.server.start;

import com.lagou.nettyrpc.server.coder.JsonSerializer;
import com.lagou.nettyrpc.server.coder.RpcDecoder;
import com.lagou.nettyrpc.server.coder.RpcEncoder;
import com.lagou.nettyrpc.server.handler.CustomRpcHandler;
import com.lagou.nettyrpc.server.pojo.RpcRequest;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * \* User: ZhuFangTao
 * \* Date: 2020/6/17 11:22 上午
 * \
 */

@Component
public class NettyRpcStater {


    @Value("${netty.rpc.port}")
    private int serverPort;

    //创建一个方法启动服务器
    @PostConstruct
    public void startServer() throws InterruptedException {
        NioEventLoopGroup bossGroup = new NioEventLoopGroup();
        NioEventLoopGroup workGroup = new NioEventLoopGroup();
        ServerBootstrap serverBootstrap = new ServerBootstrap();
        serverBootstrap.group(bossGroup, workGroup)
                .channel(NioServerSocketChannel.class)
                .childHandler(new ChannelInitializer<NioSocketChannel>() {
                    protected void initChannel(NioSocketChannel nioSocketChannel) throws Exception {
                        ChannelPipeline pipeline = nioSocketChannel.pipeline();
                        pipeline.addLast(new RpcDecoder(RpcRequest.class, new JsonSerializer()));
                        pipeline.addLast(new StringEncoder());
                        pipeline.addLast(new CustomRpcHandler());
                    }
                });
        serverBootstrap.bind(serverPort == 0 ? 8999 : serverPort).sync();
        System.out.println("ready to access invoke at port:" + serverPort);
    }
}