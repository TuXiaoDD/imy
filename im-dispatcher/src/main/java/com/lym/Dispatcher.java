package com.lym;

import com.lym.server.DispatcherServer;

import java.util.Map;

/**
 * Hello world!
 */
public class Dispatcher {
    public static void main(String[] args) {
//        Map<String, String> env = System.getenv();
//        for (Map.Entry<String, String> entry : env.entrySet()) {
//            if(entry.getKey().equals("port")){
//                DispatcherServer server = new DispatcherServer("localhost",Integer.parseInt(entry.getValue()));
//                server.start();
//            }
//        }
        DispatcherServer server = new DispatcherServer("localhost",9000);
        server.start();
    }
}
