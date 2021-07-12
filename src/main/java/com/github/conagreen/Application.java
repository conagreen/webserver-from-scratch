package com.github.conagreen;

import com.github.conagreen.webserver.WebServer;

public class Application {
    public static void main(String[] args) { new Thread(new WebServer()).start(); }
}
