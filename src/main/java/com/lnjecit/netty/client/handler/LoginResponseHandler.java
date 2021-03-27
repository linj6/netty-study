package com.lnjecit.netty.client.handler;

import com.lnjecit.netty.protocal.response.LoginResponsePacket;
import com.lnjecit.netty.session.Session;
import com.lnjecit.netty.util.SessionUtil;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

public class LoginResponseHandler extends SimpleChannelInboundHandler<LoginResponsePacket> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, LoginResponsePacket loginResponsePacket) throws Exception {
        String userId = loginResponsePacket.getUserId();
        String username = loginResponsePacket.getUsername();
        if (loginResponsePacket.isSuccess()) {
            System.out.println("[" + username + "]登录成功，userId 为：" + userId);
            Session session = new Session();
            session.setUserId(userId);
            session.setUsername(username);
            SessionUtil.bindSession(session, ctx.channel());
        } else {
            System.out.println("[" + username + "]登录失败，原因：" + loginResponsePacket.getReason());
        }
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("连接被关闭");
    }
}
