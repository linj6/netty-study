package com.lnjecit.netty.server.handler;

import com.lnjecit.netty.protocal.request.CreateGroupRequestPacket;
import com.lnjecit.netty.protocal.response.CreateGroupResponsePacket;
import com.lnjecit.netty.util.IdUtil;
import com.lnjecit.netty.util.SessionUtil;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;

import java.util.ArrayList;
import java.util.List;

public class CreateGroupRequestHandler extends SimpleChannelInboundHandler<CreateGroupRequestPacket> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, CreateGroupRequestPacket createGroupRequestPacket) throws Exception {
        List<String> userIdList = createGroupRequestPacket.getUserIdList();

        // 1.创建一个 channel 分组
        ChannelGroup channelGroup = new DefaultChannelGroup(ctx.executor());

        List<String> usernameList = new ArrayList<>();

        // 2.筛选出待加入群聊用户的 channel 和 username
        for (String userId : userIdList) {
            Channel channel = SessionUtil.getChannel(userId);
            if (channel != null) {
                channelGroup.add(channel);
                usernameList.add(SessionUtil.getSession(channel).getUsername());
            }
        }

        // 3.创建群聊结果的响应
        CreateGroupResponsePacket createGroupResponsePacket = new CreateGroupResponsePacket();
        createGroupResponsePacket.setSuccess(true);
        createGroupResponsePacket.setGroupId(IdUtil.randomId());
        createGroupResponsePacket.setUsernameList(usernameList);

        // 4.给每个用户发送拉群通知
        channelGroup.writeAndFlush(createGroupResponsePacket);

        System.out.print("群创建成功，id 为[" + createGroupResponsePacket.getGroupId() + "]，");
        System.out.println("群里面有：" + createGroupResponsePacket.getUsernameList());
    }
}
