package com.lnjecit.netty.server.handler;

import com.lnjecit.netty.protocal.request.MessageRequestPacket;
import com.lnjecit.netty.protocal.response.MessageResponsePacket;
import com.lnjecit.netty.session.Session;
import com.lnjecit.netty.util.SessionUtil;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

public class MessageRequestHandler extends SimpleChannelInboundHandler<MessageRequestPacket> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, MessageRequestPacket messageRequestPacket) throws Exception {
        // 1.拿到消息发送方的会话信息
        Session session = SessionUtil.getSession(ctx.channel());

        // 2.通过消息发送方的会话信息构造要发送的消息
        MessageResponsePacket messageResponsePacket = new MessageResponsePacket();
        messageResponsePacket.setVersion(messageRequestPacket.getVersion());
        messageResponsePacket.setMessage(messageRequestPacket.getMessage());
        messageResponsePacket.setFromUserId(session.getUserId());
        messageResponsePacket.setFromUserName(session.getUsername());

        // 3.拿到消息接收方的 channel
        String toUserId = messageRequestPacket.getToUserId();
        Channel toUserChannel = SessionUtil.getChannel(toUserId);

        // 4.将消息发送给消息接收方
        if (toUserChannel != null && SessionUtil.hasLogin(toUserChannel)) {
            toUserChannel.writeAndFlush(messageResponsePacket);
        } else {
            System.err.println("[" + messageRequestPacket.getToUserId() + "]不在线，发送失败！");
        }
        ctx.channel().writeAndFlush(messageResponsePacket);
    }
}
