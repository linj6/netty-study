package com.lnjecit.netty.server.handler;

import com.lnjecit.netty.protocal.request.JoinGroupRequestPacket;
import com.lnjecit.netty.protocal.response.JoinGroupResponsePacket;
import com.lnjecit.netty.util.SessionUtil;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;

public class JoinGroupRequestHandler extends SimpleChannelInboundHandler<JoinGroupRequestPacket> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, JoinGroupRequestPacket joinGroupRequestPacket) throws Exception {
        String groupId = joinGroupRequestPacket.getGroupId();

        ChannelGroup channelGroup = SessionUtil.getGroup(groupId);
        channelGroup.add(ctx.channel());

        JoinGroupResponsePacket joinGroupResponsePacket = new JoinGroupResponsePacket();
        joinGroupResponsePacket.setSuccess(true);
        joinGroupResponsePacket.setGroupId(groupId);
        joinGroupResponsePacket.setUsername(SessionUtil.getSession(ctx.channel()).getUsername());
        channelGroup.writeAndFlush(joinGroupResponsePacket);
    }
}
