package com.lnjecit.netty.server.handler;

import com.lnjecit.netty.protocal.request.GroupMessageRequestPacket;
import com.lnjecit.netty.protocal.response.GroupMessageResponsePacket;
import com.lnjecit.netty.util.SessionUtil;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;

public class GroupMessageRequestHandler extends SimpleChannelInboundHandler<GroupMessageRequestPacket> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, GroupMessageRequestPacket groupMessageRequestPacket) throws Exception {
        String groupId = groupMessageRequestPacket.getGroupId();
        ChannelGroup channelGroup = SessionUtil.getGroup(groupId);

        GroupMessageResponsePacket groupMessageResponsePacket = new GroupMessageResponsePacket();
        groupMessageResponsePacket.setFromGroupId(groupId);
        groupMessageResponsePacket.setFromUser(SessionUtil.getSession(ctx.channel()));
        groupMessageResponsePacket.setMessage(groupMessageRequestPacket.getMessage());

        channelGroup.writeAndFlush(groupMessageResponsePacket);
    }
}
