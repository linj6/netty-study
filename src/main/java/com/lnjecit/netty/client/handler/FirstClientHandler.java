package com.lnjecit.netty.client.handler;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.nio.charset.StandardCharsets;

public class FirstClientHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        for (int i = 0; i < 1000; i++) {
            ByteBuf byteBuf = getByteBuf(ctx);
            ctx.channel().writeAndFlush(byteBuf);
        }
    }

    private ByteBuf getByteBuf(ChannelHandlerContext ctx) {
        byte[] bytes = "草帽海贼团：路飞、索隆、娜美、乌索普、山治、乔巴、罗宾、弗兰奇、布鲁克、甚平"
                .getBytes(StandardCharsets.UTF_8);
        ByteBuf byteBuf = ctx.alloc().buffer();
        byteBuf.writeBytes(bytes);
        return byteBuf;
    }

}
