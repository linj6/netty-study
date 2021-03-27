package com.lnjecit.netty.server.handler;

import com.lnjecit.netty.protocal.request.ListGroupMembersRequestPacket;
import com.lnjecit.netty.protocal.response.ListGroupMembersResponsePacket;
import com.lnjecit.netty.session.Session;
import com.lnjecit.netty.util.SessionUtil;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;

import java.util.ArrayList;
import java.util.List;

public class ListGroupMembersRequestHandler extends SimpleChannelInboundHandler<ListGroupMembersRequestPacket> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, ListGroupMembersRequestPacket listGroupMembersRequestPacket) throws Exception {
        String groupId = listGroupMembersRequestPacket.getGroupId();
        ChannelGroup channelGroup = SessionUtil.getGroup(groupId);

        List<Session> sessionList = new ArrayList<>();

        for (Channel channel : channelGroup) {
            sessionList.add(SessionUtil.getSession(channel));
        }

        ListGroupMembersResponsePacket listGroupMembersResponsePacket = new ListGroupMembersResponsePacket();
        listGroupMembersResponsePacket.setGroupId(groupId);
        listGroupMembersResponsePacket.setSessionList(sessionList);

        ctx.channel().writeAndFlush(listGroupMembersResponsePacket);
    }
}
