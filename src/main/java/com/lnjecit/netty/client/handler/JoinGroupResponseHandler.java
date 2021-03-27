package com.lnjecit.netty.client.handler;

import com.lnjecit.netty.protocal.response.JoinGroupResponsePacket;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

public class JoinGroupResponseHandler extends SimpleChannelInboundHandler<JoinGroupResponsePacket> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, JoinGroupResponsePacket joinGroupResponsePacket) throws Exception {
        if (joinGroupResponsePacket.isSuccess()) {
            System.out.println("[" + joinGroupResponsePacket.getUsername() + "]加入群[" + joinGroupResponsePacket.getGroupId() + "]成功");
        } else {
            System.out.println("[" + joinGroupResponsePacket.getUsername() + "]加入群[" + joinGroupResponsePacket.getGroupId() + "]失败，失败原因为：" + joinGroupResponsePacket.getReason());
        }
    }
}
