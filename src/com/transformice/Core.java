package com.transformice;

import com.transformice.server.Server;
import com.transformice.network.Bootstrap;
import com.transformice.server.helpers.Rooms;
import com.transformice.server.helpers.Sessions;
import com.transformice.server.helpers.Tribulle;
import com.transformice.server.helpers.Users;

import java.net.InetSocketAddress;

public class Core {

    private Server server;

    public static void main(String... args) {
        new Core().start();
    }

    public void start() {
        this.server = new Server();
        for (int port : new int[] {57}) {
            this.server.sessions = new Sessions(this.server);
            this.server.users = new Users(this.server);
            this.server.rooms = new Rooms(this.server);
            this.server.tribulle = new Tribulle(this.server);
            this.server.channels.add(new Bootstrap(this.server).boot().bind(new InetSocketAddress(port)));
        }
    }
}
