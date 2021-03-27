package com.lnjecit.netty.util;


import com.lnjecit.netty.attribute.Attributes;
import com.lnjecit.netty.session.Session;
import io.netty.channel.Channel;
import io.netty.channel.group.ChannelGroup;
import io.netty.util.Attribute;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class SessionUtil {

    private static final Map<String, Channel> USER_ID_CHANNEL_MAP = new ConcurrentHashMap<>();
    private static final Map<String, ChannelGroup> GROUP_ID_CHANNEL_GROUP_MAP = new ConcurrentHashMap<>();

    public static void bindSession(Session session, Channel channel) {
        USER_ID_CHANNEL_MAP.put(session.getUserId(), channel);
        channel.attr(Attributes.LOGIN).set(session);
    }

    public static void unbindSession(Channel channel) {
        if (hasLogin(channel)) {
            Session session = getSession(channel);
            USER_ID_CHANNEL_MAP.remove(session.getUserId());
            channel.attr(Attributes.LOGIN).set(null);
            System.out.println(session + " 退出登录!");
        }
    }

    public static Session getSession(Channel channel) {
        return channel.attr(Attributes.LOGIN).get();
    }

    public static Channel getChannel(String userId) {
        return USER_ID_CHANNEL_MAP.get(userId);
    }

    public static boolean hasLogin(Channel channel) {
        Attribute<Session> loginAttr = channel.attr(Attributes.LOGIN);
        return loginAttr.get() != null;
    }

    public static void bindGroup(String groupId, ChannelGroup channelGroup) {
        GROUP_ID_CHANNEL_GROUP_MAP.put(groupId, channelGroup);
    }

    public static ChannelGroup getGroup(String groupId) {
        return GROUP_ID_CHANNEL_GROUP_MAP.get(groupId);
    }
}
