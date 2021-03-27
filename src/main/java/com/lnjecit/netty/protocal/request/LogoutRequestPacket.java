package com.lnjecit.netty.protocal.request;

import com.lnjecit.netty.protocal.Packet;
import com.lnjecit.netty.protocal.command.Command;

public class LogoutRequestPacket extends Packet {
    @Override
    public Byte getCommand() {
        return Command.LOGOUT_REQUEST;
    }
}
