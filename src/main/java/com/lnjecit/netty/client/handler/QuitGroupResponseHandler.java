package com.lnjecit.netty.client.handler;

import com.lnjecit.netty.protocal.response.QuitGroupResponsePacket;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

public class QuitGroupResponseHandler extends SimpleChannelInboundHandler<QuitGroupResponsePacket> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, QuitGroupResponsePacket quitGroupResponsePacket) throws Exception {
        if (quitGroupResponsePacket.isSuccess()) {
            System.out.println("[" + quitGroupResponsePacket.getUsername() + "]退出群聊[" + quitGroupResponsePacket.getGroupId() + "]成功");
        } else {
            System.out.println("[" + quitGroupResponsePacket.getUsername() + "]退出群聊[" + quitGroupResponsePacket.getGroupId() + "]失败，原因为：" + quitGroupResponsePacket.getUsername());
        }
    }
}
