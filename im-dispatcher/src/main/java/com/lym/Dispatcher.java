package com.lym;

import com.lym.server.DispatcherServer;

/**
 * Hello world!
 */
public class Dispatcher {
    public static void main(String[] args) {
        DispatcherServer server = new DispatcherServer("127.0.0.1",9000);
        server.start();

    }
}
