package com.marceloserpa.karyon.rxnetty.app;

import netflix.adminresources.resources.KaryonWebAdminModule;
import netflix.karyon.Karyon;
import netflix.karyon.KaryonBootstrapModule;
import netflix.karyon.ShutdownModule;
import netflix.karyon.archaius.ArchaiusBootstrapModule;
import netflix.karyon.servo.KaryonServoModule;

public class Server {

    public static final int SERVER_PORT = 9000;

    public static void main(String[] args) {
        System.setProperty("java.awt.headless","true");
        System.setProperty("archaius.deployment.environment","dev");

        Karyon.forRequestHandler(SERVER_PORT,
                new RxNettyHandler(),
                new KaryonBootstrapModule(new HealthChecker()),
                new ArchaiusBootstrapModule("sample"),
                Karyon.toBootstrapModule(KaryonWebAdminModule.class),
                ShutdownModule.asBootstrapModule(),
                KaryonServoModule.asBootstrapModule()
        ).startAndWaitTillShutdown();

    }

}
