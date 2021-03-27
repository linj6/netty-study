package com.lnjecit.netty.protocal.request;

import com.lnjecit.netty.protocal.Packet;
import com.lnjecit.netty.protocal.command.Command;
import lombok.Data;

@Data
public class ListGroupMembersRequestPacket extends Packet {

    private String groupId;

    @Override
    public Byte getCommand() {
        return Command.LIST_GROUP_MEMBERS_REQUEST;
    }
}
