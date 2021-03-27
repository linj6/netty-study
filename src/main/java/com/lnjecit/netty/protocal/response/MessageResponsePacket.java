package com.lnjecit.netty.protocal.response;

import com.lnjecit.netty.protocal.Packet;
import com.lnjecit.netty.protocal.command.Command;
import lombok.Data;

@Data
public class MessageResponsePacket extends Packet {

    private String message;

    @Override
    public Byte getCommand() {
        return Command.MESSAGE_RESPONSE;
    }
}
