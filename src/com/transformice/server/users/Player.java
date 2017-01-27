package com.transformice.server.users;

import com.transformice.network.packet.ByteArray;
import com.transformice.network.packet.Identifiers;
import com.transformice.server.Server;
import org.apache.commons.lang3.StringUtils;
import org.jboss.netty.buffer.ChannelBuffers;
import org.jboss.netty.channel.Channel;

public class Player {
    public int lastpacket = 0;
    public int authkey = 0;
    public int code = 0;
    public int id = 0;
    public int privlevel = 1;
    public int gender = 0;
    public int score = 0;
    public int title = 0;
    public int title_star = 0;

    public long playerStartTimeMillis = 0;

    public byte langueByte = 0;

    public boolean guest = false;
    public boolean hidden = false;
    public boolean dead = false;
    public boolean cheese = false;
    public boolean isnew = false;
    public boolean sync = false;
    public boolean tribunal = false;

    public String username = "";
    public String nameColor = "";
    public String mousename = "Souris";
    public String roomName;
    public String langue = "";
    public String url = "";
    public String fur = "";
    public String look = "1;0,0,0,0,0,0,0,0,0";
    public String color = "78583a";
    public String shaman_color = "95d9d6";
    public String marriage = "";

    public Server server;
    public Channel channel;

    public Player(Server server, Channel channel) {
        this.server = server;
        this.channel = channel;
    }

    public void close() {
        this.server.users.lost(this.channel);
    }

    public void startPlay() {
        this.playerStartTimeMillis = this.server.rooms.timers.get(this.roomName).get("gameStartTimeMillis");
        this.isnew = this.server.rooms._info.get(roomName).get("isCurrentlyPlay") == 0 ? false : true;
        this.sendPacket(Identifiers.room.send.new_map, this.server.rooms.sendNewMap(this, 1).toByteArray());
        this.sendOldPacket(Identifiers.old.room.send.player_list, this.server.rooms.getPlayerList(this.roomName));
        int sync = this.server.rooms._info.get(this.roomName).get("syncCode");
        this.sendOldPacket(Identifiers.old.room.send.sync, this.server.rooms.sendSync(this, sync));
        if (this.code == sync) {
            this.sync = true;
        }
        this.sendPacket(Identifiers.room.send.round_time, new ByteArray().writeShort(this.server.rooms._info.get(this.roomName).get("time") + (this.server.rooms._info.get(this.roomName).get("gameStartTime") - this.server.getTime())).toByteArray());
        if (this.server.rooms._info.get(this.roomName).get("isCurrentlyPlay") == 1) {
            if (this.cheese) {
                this.cheese = false;
                this.server.rooms.sendAll(this.roomName, Identifiers.room.send.remove_cheese, new ByteArray().writeInt(this.code).toByteArray());
            }

            this.sendPacket(Identifiers.room.send.map_start_timer, 0);
        } else {
            this.sendPacket(Identifiers.room.send.map_start_timer, 1);
        }
        this.sendPacket(new int[]{5, 51}, new ByteArray().writeByte(3).writeByte(-18).writeByte(1).writeShort(54).writeShort(-100).toByteArray());
        System.out.print("sasds");
    }

    public void sendPacket(int[] identifiers, byte... data) {
        ByteArray packet = new ByteArray();
        int length = data.length + 2;
        if (length <= 0xFF) {
            packet.writeByte(1).writeByte(length);
        } else if (length <= 0xFFFF) {
            packet.writeByte(2).writeShort(length);
        } else if (length <= 0xFFFFFF) {
            packet.writeByte(3).writeByte((length >> 16) & 0xFF).writeByte((length >> 8) & 0xFF).writeByte(length & 0xFF);
        }
        packet.writeByte(identifiers[0]).writeByte(identifiers[1]).writeBytes(data);
        this.channel.write(ChannelBuffers.wrappedBuffer(packet.toByteArray()));
    }

    public final void sendPacket(int[] identifiers, int... data) {
        byte[] result = new byte[identifiers.length];
        for (int i = 0; i < identifiers.length; i++) {
            result[i] = (byte) identifiers[i];
        }
        this.sendPacket(identifiers, result);
    }

    private void sendPacket(int[] identifiers, String packet) {
        this.sendPacket(identifiers, packet.getBytes());
    }

    public void sendOldPacket(int[] identifiers, Object... values) {
        ByteArray packet = new ByteArray();
        String data = values.length == 0 ? "" : '\u0001' + StringUtils.join(values, '\u0001');
        int length = data.length() + 6;
        if (length <= 0xFF) {
            packet.writeByte(1).writeByte(length);
        } else if (length <= 0xFFFF) {
            packet.writeByte(2).writeShort(length);
        } else if (length <= 0xFFFFFF) {
            packet.writeByte(3).writeByte((length >> 16) & 0xFF).writeByte((length >> 8) & 0xFF).writeByte(length & 0xFF);
        }
        packet.writeByte(1).writeByte(1).writeShort(data.length() + 2).writeByte(identifiers[0]).writeByte(identifiers[1]).writeBytes(data);
        this.channel.write(ChannelBuffers.wrappedBuffer(packet.toByteArray()));
    }
}
