package com.lnjecit.netty.protocal.response;

import com.lnjecit.netty.protocal.Packet;
import com.lnjecit.netty.protocal.command.Command;
import lombok.Data;

@Data
public class QuitGroupResponsePacket extends Packet {

    private String groupId;

    private String username;

    private boolean success;

    private String reason;

    @Override
    public Byte getCommand() {
        return Command.QUIT_GROUP_RESPONSE;
    }
}
