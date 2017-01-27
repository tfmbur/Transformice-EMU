package com.transformice.server.helpers;

import com.transformice.network.packet.ByteArray;
import com.transformice.network.packet.Identifiers;
import com.transformice.server.Server;
import com.transformice.server.users.Player;

public class Tribulle {
    private Server server;

    public Tribulle(Server server) {
        this.server = server;
    }

    private void sendPacket(Player player, int code, byte[] result) {
        ByteArray packet = new ByteArray();
        packet.writeShort(code);
        packet.write(result);
        player.sendPacket(Identifiers.tribulle.send.tokens, packet.toByteArray());
    }

    public void sendPlayerInfo(Player player) {
        ByteArray packet = new ByteArray();
        packet.writeInt(0);
        packet.writeInt(player.id);
        packet.writeInt(player.id);
        packet.writeInt(this.getInGenderMarriage(player.username));
        packet.writeInt(!player.marriage.equals("") ? this.server.users.getPlayerID(player.marriage) : 0);
        packet.writeUTF(player.marriage);

        this.sendPacket(player, Identifiers.tribulle.send.ET_ReponseDemandeInfosJeuUtilisateur, packet.toByteArray());
    }

    private int getInGenderMarriage(String playerName) {
        int gender;
        String marriage;
        if (this.server.users.players.containsKey(playerName)) {
            Player player = (Player) this.server.users.players.get(playerName);
            gender = player.gender;
            marriage = player.marriage;
        } else {
            gender = this.getPlayerGender(playerName);
            marriage = this.getPlayerMarriage(playerName);
        }
        return marriage.equals("") ? (gender == 1 ? 5 : gender == 2 ? 9 : 1) : (gender == 1 ? 7 : gender == 2 ? 11 : 3);
    }

    private int getPlayerGender(String playerName) {
        return 0;
    }

    private String getPlayerMarriage(String playerName) {
        return "";
    }
}
