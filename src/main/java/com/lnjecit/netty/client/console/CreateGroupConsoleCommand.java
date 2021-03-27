package com.lnjecit.netty.client.console;

import com.lnjecit.netty.protocal.request.CreateGroupRequestPacket;
import io.netty.channel.Channel;

import java.util.Arrays;
import java.util.Scanner;

public class CreateGroupConsoleCommand implements ConsoleCommand {

    private static final String USER_ID_SPLITER = ",";

    @Override
    public void exec(Scanner scanner, Channel channel) {
        CreateGroupRequestPacket createGroupRequestPacket = new CreateGroupRequestPacket();

        System.out.print("【拉人群聊】输入 userId 列表, userId 之间用英文逗号隔开：");
        String userIds = scanner.next();
        String[] userIdList = userIds.split(USER_ID_SPLITER);
        createGroupRequestPacket.setUserIdList(Arrays.asList(userIdList));
        channel.writeAndFlush(createGroupRequestPacket);
    }
}
