package com.lnjecit.netty.protocal.response;

import com.lnjecit.netty.protocal.Packet;
import com.lnjecit.netty.protocal.command.Command;
import lombok.Data;

@Data
public class LoginResponsePacket extends Packet {

    private boolean success;

    private String reason;

    @Override
    public Byte getCommand() {
        return Command.LOGIN_RESPONSE;
    }
}
