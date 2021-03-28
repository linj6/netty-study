package com.lnjecit.netty.server.handler;

import com.lnjecit.netty.protocal.request.HeartBeatRequestPacket;
import com.lnjecit.netty.protocal.response.HeartBeatResponsePacket;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

public class HeartBeatTimerRequestHandler extends SimpleChannelInboundHandler<HeartBeatRequestPacket> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, HeartBeatRequestPacket msg) throws Exception {
        ctx.channel().writeAndFlush(new HeartBeatResponsePacket());
    }
}
