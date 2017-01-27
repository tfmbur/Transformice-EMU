package com.transformice.server.helpers;

import com.transformice.server.Server;
import com.transformice.server.users.Player;
import org.apache.commons.lang3.StringUtils;
import org.jboss.netty.channel.Channel;
import org.jboss.netty.util.internal.ConcurrentHashMap;

import java.util.Map;

public class Users {
    private Server server;

    public int lastPlayerCode = 0;

    public Map<String, Object> players = new ConcurrentHashMap();

    public Users(Server server) {
        this.server = server;
    }

    public Object get(Channel channel) {
        return channel.getAttachment();
    }

    public void lost(Channel channel) {
        channel.close();
    }

    public String getPlayerData(Player player) {
        return StringUtils.join(new Object[]{!player.tribunal ? (!player.mousename.isEmpty() ? player.mousename : player.username) : "Souris", player.code, 1, player.dead ? 1 : 0, player.score, player.cheese ? 1 : 0, !player.tribunal ? (player.title + "," + player.title_star) : "0,0", 0, player.fur.isEmpty() ? (!player.tribunal && this.server.rooms._info.get(player.roomName).get("mode") != 13 ? player.look : "1;0,0,0,0,0,0,0,0,0") : player.fur, 0, player.color, player.shaman_color, 0}, "#") + (player.tribunal ? "#-1" : !player.nameColor.isEmpty() ? ("#" + player.nameColor) : "");
    }

    public boolean checkConnectedAccount(String playerName) {
        return this.players.containsKey(playerName);
    }

    public int getPlayerID(String playerName) { return 1; }
}
