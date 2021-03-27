package com.lnjecit.netty.server.handler;

import com.lnjecit.netty.protocal.request.QuitGroupRequestPacket;
import com.lnjecit.netty.protocal.response.QuitGroupResponsePacket;
import com.lnjecit.netty.util.SessionUtil;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;

public class QuitGroupRequestHandler extends SimpleChannelInboundHandler<QuitGroupRequestPacket> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, QuitGroupRequestPacket quitGroupRequestPacket) throws Exception {
        String groupId = quitGroupRequestPacket.getGroupId();
        ChannelGroup channelGroup = SessionUtil.getGroup(groupId);
        channelGroup.remove(ctx.channel());

        QuitGroupResponsePacket quitGroupResponsePacket = new QuitGroupResponsePacket();
        quitGroupResponsePacket.setGroupId(groupId);
        quitGroupResponsePacket.setUsername(SessionUtil.getSession(ctx.channel()).getUsername());
        quitGroupResponsePacket.setSuccess(true);

        ctx.channel().writeAndFlush(quitGroupResponsePacket);
    }
}
