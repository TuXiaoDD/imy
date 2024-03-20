package com.lym;

import java.io.IOException;
import java.util.Scanner;

public class Client {

    public static void main(String[] args) throws IOException {

        ImClient client = new ImClient("localhost", 9999);
        client.start();
        client.auth(1L, "token");

        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            String s = sc.nextLine();
            client.send(s);
        }
    }
}
