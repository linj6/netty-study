package com.lnjecit.netty.session;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Session {
    private String userId;
    private String username;

    @Override
    public String toString() {
        return userId + ":" + username;
    }
}
