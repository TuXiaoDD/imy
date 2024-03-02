package com.lym;

public class Main {

    public static void main(String[] args) {

        ImClient client = new ImClient("localhost",9999);
        client.start();
        client.auth(1L,"token");
        client.send("hello");

        while (true){

        }
    }
}
