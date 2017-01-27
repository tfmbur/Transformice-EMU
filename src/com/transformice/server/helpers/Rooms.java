package com.transformice.server.helpers;

import com.transformice.network.packet.ByteArray;
import com.transformice.network.packet.Identifiers;
import com.transformice.server.Server;
import com.transformice.server.users.Player;
import org.apache.commons.lang3.StringUtils;
import org.jboss.netty.util.internal.ConcurrentHashMap;

import java.util.*;
import java.util.Map.Entry;

public class Rooms {

    public Map<String, HashMap<String, Player>> rooms = new ConcurrentHashMap();
    public Map<String, HashMap<String, Integer>> _info = new ConcurrentHashMap();
    public Map<String, HashMap<String, Long>> timers = new ConcurrentHashMap();
    public Map<String, Object[]> anchors = new ConcurrentHashMap();

    private Server server;

    public Rooms(Server server) {
        this.server = server;
    }

    public void enterRoom(Player player, String roomName) {
        roomName = roomName.replace("<", "&lt;");
        if (roomName.contains((char) 3 + "[Editeur] ") || roomName.contains((char) 3 + "[Totem] ") || roomName.contains((char) 3 + "[Tutorial] ")) {
            String nameCheck = StringUtils.split(roomName, " ")[1];
            if (!nameCheck.equals(player.username)) {
                player.close();
            }
        }
        if (!roomName.startsWith("*") && !(roomName.length() > 3 && roomName.charAt(2) == '-' && player.privlevel >= 7)) {
            roomName = player.langue + "-" + roomName;
        }
        if (this.checkRoom(roomName, player.langue) == 0) {
            HashMap players = new HashMap();
            players.put(player.username, player);
            this.rooms.put(roomName, players);
            HashMap info = new HashMap();
            info.put("code", 25);
            info.put("eCode", 25);
            info.put("max", 25);
            info.put("mode", 1);
            info.put("time", 120);
            info.put("syncCode", 0);
            info.put("isCurrentlyPlay", 1);
            info.put("lastCodePartie", 0);
            info.put("gameStartTime", this.server.getTime());
            this._info.put(roomName, info);
            HashMap timers = new HashMap();
            timers.put("gameStartTimeMillis", System.currentTimeMillis());
            this.timers.put(roomName, timers);
            this.anchors.put(roomName, new Object[]{});
        } else {
            this.rooms.get(roomName).put(player.username, player);
        }
        player.roomName = roomName;
        player.dead = this._info.get(roomName).get("isCurrentlyPlay") == 0 ? false : true;
        if (!player.hidden) {
            this.sendAllOthersOld(roomName, player, Identifiers.old.room.send.respawn, this.server.users.getPlayerData(player));
        }
        player.sendPacket(new int[]{29, 1});
        player.sendPacket(Identifiers.room.send.enter, new ByteArray().writeBoolean(roomName.startsWith("*") || roomName.startsWith(String.valueOf((char) 3))).writeUTF(roomName).toByteArray());
        player.sendOldPacket(Identifiers.old.room.send.anchors, this.anchors.get(roomName));
        player.startPlay();
    }

    public Object[] getPlayerList(String roomName) {
        List<String> result = new ArrayList(this.rooms.get(roomName).values().size());
        for (Player player : this.rooms.get(roomName).values()) {
            if (!player.hidden) {
                result.add(this.server.users.getPlayerData(player));
            }
        }
        return result.toArray();
    }

    public ByteArray sendNewMap(Player player, int mapNum) {
        return new ByteArray().writeInt(mapNum).writeShort(this.rooms.values().size()).writeByte(this._info.get(player.roomName).get("lastCodePartie").intValue()).writeUTF("").writeUTF("").writeByte(0).writeByte(0);
    }

    public Object sendSync(Player player, int playerCode) {
        return (this._info.get(player.roomName).get("code") != -1 || this._info.get(player.roomName).get("eCode") != 0) ? new Object[]{playerCode, ""} : new Object[]{playerCode};
    }

    public int checkRoom(String roomName, String langue) {
        if (this.rooms.containsKey(!roomName.startsWith("*") && roomName.charAt(0) != (char) 3 ? (langue + "-" + roomName) : roomName)) {
            if (this.rooms.values().size() < this._info.get(roomName).get("max")) {
                return 2; // full
            } else {
                return 1; // enter
            }
        } else {
            return 0; // room not found
        }
    }

    public void sendAll(String roomName, int[] identifiers, byte... packet) {
        for (Player player : this.rooms.get(roomName).values()) {
            player.sendPacket(identifiers, packet);
        }
    }

    public void sendAllOthersOld(String roomName, Player senderPlayer, int[] identifiers, Object... packet) {
        for (Player player : this.rooms.get(roomName).values()) {
            if (!player.equals(senderPlayer)) {
                player.sendOldPacket(identifiers, packet);
            }
        }
    }

}
