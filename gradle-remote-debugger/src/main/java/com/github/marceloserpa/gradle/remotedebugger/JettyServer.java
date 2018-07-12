package com.github.marceloserpa.gradle.remotedebugger;

import org.eclipse.jetty.server.Connector;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.ServerConnector;
import org.eclipse.jetty.servlet.ServletHandler;

public class JettyServer {

    private Server server;

    public void start(int port) throws Exception {
        server = new Server();
        ServerConnector connector = new ServerConnector(server);
        connector.setPort(port);

        ServletHandler handler = new ServletHandler();
        server.setHandler(handler);

        handler.addServletWithMapping(HelloServlet.class, "/*");
        server.setConnectors(new Connector[] {connector});


        server.start();
        server.join();
    }
}
