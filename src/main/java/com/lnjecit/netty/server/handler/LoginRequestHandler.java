package com.lnjecit.netty.server.handler;

import com.lnjecit.netty.protocal.request.LoginRequestPacket;
import com.lnjecit.netty.protocal.response.LoginResponsePacket;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.util.Date;

public class LoginRequestHandler extends SimpleChannelInboundHandler<LoginRequestPacket> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, LoginRequestPacket loginRequestPacket) throws Exception {
        // 登录流程
        LoginResponsePacket loginResponsePacket = login(loginRequestPacket);
        // 登录响应
        ctx.channel().writeAndFlush(loginResponsePacket);
    }

    private LoginResponsePacket login(LoginRequestPacket loginRequestPacket) {
        System.out.println(new Date() + ": 收到客户端登录请求……");

        LoginResponsePacket loginResponsePacket = new LoginResponsePacket();
        loginResponsePacket.setVersion(loginRequestPacket.getVersion());
        if (valid(loginRequestPacket)) {
            loginResponsePacket.setSuccess(true);
            System.out.println(new Date() + "：登录成功！");
        } else {
            loginResponsePacket.setSuccess(false);
            loginResponsePacket.setReason("账号密码校验失败");
            System.out.println(new Date() + "：登录失败！");
        }
        return loginResponsePacket;
    }

    private boolean valid(LoginRequestPacket loginRequestPacket) {
        return true;
    }

}
