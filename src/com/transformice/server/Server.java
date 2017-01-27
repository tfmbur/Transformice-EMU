package com.transformice.server;

import com.transformice.server.helpers.Rooms;
import com.transformice.server.helpers.Sessions;
import com.transformice.server.helpers.Tribulle;
import com.transformice.server.helpers.Users;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.jboss.netty.channel.Channel;

import java.util.ArrayList;
import java.util.List;

public class Server {
    public List<Channel> channels = new ArrayList();

    public Sessions sessions;
    public Users users;
    public Rooms rooms;
    public Tribulle tribulle;

    public int[] packetKeys = {55,62,55,25,29,50,5,10,38,32,109,100,105,71,71,104,99,108,76,74};
    public int[] loginKeys = {-2147483648,-2147483648,256,16777216,13326141,256,16777216,10915256};

    public String parsePlayerName(String playerName) {
        return playerName.startsWith("*") ? "*" + StringUtils.capitalize(playerName.substring(1).toLowerCase()) : StringUtils.capitalize(playerName.toLowerCase());
    }

    public String getRandomChars(int size) {
        return RandomStringUtils.random(size, "ABCDEF123456789");
    }

    public int getTime() {
        return (int) (System.currentTimeMillis() / 1000);
    }
}
