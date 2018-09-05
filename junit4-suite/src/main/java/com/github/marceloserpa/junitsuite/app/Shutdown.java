package com.github.marceloserpa.junitsuite.app;

public class Shutdown {

    public String shutdownDB(String db){
        if(db.equals("MYSQL")) return "shutdown " + db;
        else if(db.equals("MARIADB")) return "shutdown " + db;
        else throw new RuntimeException("Unsupported DB");
    }

}
