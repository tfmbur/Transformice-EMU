package com.transformice.network.packet;

import com.transformice.server.Server;
import com.transformice.server.users.Player;
import org.jboss.netty.channel.ChannelHandlerContext;

import java.util.concurrent.ThreadLocalRandom;

public class ParsePackets {
    private Server server;

    public ParsePackets(Server server) {
        this.server = server;
    }

    public ByteArray decrypt(int packetID, ByteArray packet, int[] keys) {
        ByteArray data = new ByteArray();
        while (packet.bytesAvailable()) {
            packetID = ++packetID % keys.length;
            data.writeByte(packet.readByte() ^ keys[packetID]);
        }
        return data;
    }

    public void parsePacket(ChannelHandlerContext context, ByteArray packet, int packetID) {
        byte[] token = {packet.readByte(), packet.readByte()};
        if (token[0] == 28 && token[1] == 1) {
            if (this.server.sessions.contains(context.getChannel())) {
                this.server.sessions.register(context.getChannel());
                Player player = (Player) this.server.users.get(context.getChannel());
                player.sendPacket(Identifiers.screen.send.version, new ByteArray().writeInt(this.server.users.players.size()).writeByte(player.lastpacket = ThreadLocalRandom.current().nextInt(0, 99)).writeUTF(player.langue.toLowerCase()).writeUTF(player.langue.toLowerCase()).writeInt(player.authkey = ThreadLocalRandom.current().nextInt(0, Integer.MAX_VALUE)).toByteArray());
                player.sendPacket(Identifiers.screen.send.banner, 52, 0);
                player.sendPacket(Identifiers.screen.send.image, new ByteArray().writeUTF("x_noel2014.jpg").toByteArray());
            }
        } else {
            Player player = (Player) this.server.users.get(context.getChannel());
            if (token[0] == Identifiers.screen.send.login[0] && token[1] == Identifiers.screen.send.login[1]) {
                if (player.username.isEmpty()) {
                    packet = this.decrypt(packetID, packet, this.server.packetKeys);
                    String playerName = this.server.parsePlayerName(packet.readUTF());
                    String password = packet.readUTF();
                    player.url = packet.readUTF();
                    String startRoom = packet.readUTF();
                    int resultKey = packet.readInt();
                    int authKey = player.authkey;
                    for (int key : this.server.loginKeys) {
                        authKey ^= key;
                    }
                    if (!playerName.matches("^[A-Za-z][A-Za-z0-9_]{2,11}$") || playerName.length() > 25 || (playerName.length() >= 1 && playerName.substring(1).contains("+"))) {
                        player.close();
                    } else if (authKey == resultKey) {
                        playerName = playerName.equals("") ? "Souris" : playerName;
                        if (password.equals("")) {
                            if (!this.server.users.checkConnectedAccount(playerName)) {
                                playerName = playerName + "_" + this.server.getRandomChars(6).toLowerCase();
                            }
                            startRoom = (char) 3 + "[Tutorial] " + playerName;
                            player.guest = true;
                        }
                        if (this.server.users.checkConnectedAccount(playerName)) {
                            player.sendPacket(Identifiers.screen.send.result, 1);
                        } else {
                            if (!player.guest) {

                            }
                            player.username = playerName;
                            player.code = ++this.server.users.lastPlayerCode;
                            if (player.guest) {
                                player.sendPacket(Identifiers.screen.send.souris, new ByteArray().writeByte(1).writeByte(10).toByteArray());
                                player.sendPacket(Identifiers.screen.send.souris, new ByteArray().writeByte(2).writeByte(5).toByteArray());
                                player.sendPacket(Identifiers.screen.send.souris, new ByteArray().writeByte(3).writeByte(15).toByteArray());
                                player.sendPacket(Identifiers.screen.send.souris, new ByteArray().writeByte(4).writeByte(200).toByteArray());
                            }
                            player.sendPacket(Identifiers.screen.send.identification, new ByteArray().writeInt(player.id).writeUTF(player.username).writeInt(600000).writeByte(player.langueByte).writeInt(player.code).writeByte(player.privlevel).writeByte(0).writeBoolean(false).toByteArray());
                            player.sendPacket(Identifiers.room.send.time_stamp, new ByteArray().writeInt(this.server.getTime()).toByteArray());
                            this.server.tribulle.sendPlayerInfo(player);
                            this.server.rooms.enterRoom(player, startRoom);
                            this.server.users.players.put(player.username, player);
                        }
                    }
                }
            }
        }
    }
}
