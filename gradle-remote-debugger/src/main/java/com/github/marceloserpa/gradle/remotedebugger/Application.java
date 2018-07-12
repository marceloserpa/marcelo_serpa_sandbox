package com.github.marceloserpa.gradle.remotedebugger;

public class Application {

    public static void main(String[] args) throws Exception {

        JettyServer server = new JettyServer();
        server.start(9000);

    }

}
