package com.lnjecit.netty.attribute;

import com.lnjecit.netty.session.Session;
import io.netty.util.AttributeKey;

public interface Attributes {

    AttributeKey<Session> LOGIN = AttributeKey.newInstance("session");
}
