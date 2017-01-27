package com.transformice.server.helpers;

import com.transformice.server.Server;
import com.transformice.server.users.Player;
import org.jboss.netty.channel.Channel;
import org.jboss.netty.util.internal.ConcurrentHashMap;

import java.util.Map;

public class Sessions {
    private final Map<Integer, Channel> sessions = new ConcurrentHashMap();
    private final Map<Integer, byte[]> incompletePackets = new ConcurrentHashMap();
    private Server server;

    public Sessions(Server server) {
        this.server = server;
    }

    public void addSession(Channel channel) {
        this.sessions.put(channel.getId(), channel);
    }

    public void removeSession(Channel channel) {
        this.sessions.remove(channel.getId());
    }

    public void register(Channel channel) {
        channel.setAttachment(new Player(this.server, channel));
    }

    public boolean contains(Channel channel) {
        return this.sessions.containsKey(channel.getId());
    }

    public boolean checkIncompletePacket(Channel channel) {
        return this.incompletePackets.containsKey(channel.getId());
    }

    public byte[] getIncompletePacket(Channel channel) {
        byte[] buff = this.incompletePackets.get(channel.getId());
        this.incompletePackets.remove(channel.getId());
        return buff;
    }

    public void putIncompletePacket(Channel channel, byte[] buff) {
        this.incompletePackets.put(channel.getId(), buff);
    }
}
