package com.demo.netty.server;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class NettyServer {
    private static final Logger LOGGER = LoggerFactory.getLogger(NettyServer.class);

    public static void main(String[] args) {
        System.setProperty("io.netty.eventLoopThreads", "4");
        new ServerBootstrap().group(new NioEventLoopGroup()).channel(NioServerSocketChannel.class)
                .childHandler(
                        new ChannelInitializer<NioSocketChannel>() {
                            @Override
                            protected void initChannel(NioSocketChannel ch) throws Exception {
                                ch.pipeline().addLast(new StringDecoder()); // 5
                                ch.pipeline().addLast(new SimpleChannelInboundHandler<String>() { // 6
                                    @Override
                                    protected void channelRead0(ChannelHandlerContext ctx, String msg) {
                                        LOGGER.info(msg);
                                    }
                                });
                            }
                        }
                ).bind(7080);
    }
}
