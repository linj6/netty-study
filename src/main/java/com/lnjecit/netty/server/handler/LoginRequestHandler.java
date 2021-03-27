package com.lnjecit.netty.server.handler;

import com.lnjecit.netty.protocal.request.LoginRequestPacket;
import com.lnjecit.netty.protocal.response.LoginResponsePacket;
import com.lnjecit.netty.session.Session;
import com.lnjecit.netty.util.IdUtil;
import com.lnjecit.netty.util.SessionUtil;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.util.Date;

public class LoginRequestHandler extends SimpleChannelInboundHandler<LoginRequestPacket> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, LoginRequestPacket loginRequestPacket) throws Exception {
        // 登录流程
        System.out.println(new Date() + ": 收到客户端登录请求……");

        LoginResponsePacket loginResponsePacket = new LoginResponsePacket();
        loginResponsePacket.setVersion(loginRequestPacket.getVersion());
        if (valid(loginRequestPacket)) {
            loginResponsePacket.setSuccess(true);
            String userId = IdUtil.randomId();
            String username = loginRequestPacket.getUsername();
            loginResponsePacket.setUserId(userId);
            loginResponsePacket.setUsername(username);
            System.out.println("[" + username + "]登录成功！");
            Session session = new Session();
            session.setUserId(userId);
            session.setUsername(username);
            SessionUtil.bindSession(session, ctx.channel());
        } else {
            loginResponsePacket.setSuccess(false);
            loginResponsePacket.setReason("账号密码校验失败");
            System.out.println(new Date() + "：登录失败！");
        }
        // 登录响应
        ctx.channel().writeAndFlush(loginResponsePacket);
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        SessionUtil.unbindSession(ctx.channel());
    }

    private boolean valid(LoginRequestPacket loginRequestPacket) {
        return true;
    }

}
