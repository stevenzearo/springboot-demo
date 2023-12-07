package com.demo.netty.client;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.string.StringEncoder;

import java.time.ZonedDateTime;

public class NettyClient {
    public static void main(String[] args) throws InterruptedException {
        Channel channel = new Bootstrap()
                .group(new NioEventLoopGroup()) // 1
                .channel(NioSocketChannel.class) // 2
                .handler(new ChannelInitializer<Channel>() { // 3
                    @Override
                    protected void initChannel(Channel ch) {
                        ch.pipeline().addLast(new StringEncoder()); // 8
                    }
                })
                .connect("127.0.0.1", 7080) // 4
                .sync() // 5
                .channel();
        ChannelFuture channelFuture = channel.writeAndFlush(ZonedDateTime.now() + ":hello, world!");
        while (channelFuture.isSuccess()) {
            channel = channelFuture.channel();
            channelFuture = channel.writeAndFlush(ZonedDateTime.now() + ":hello, world!");
            Thread.sleep(500);
        }

    }

}
