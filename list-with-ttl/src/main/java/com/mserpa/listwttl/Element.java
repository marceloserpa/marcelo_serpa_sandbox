package com.mserpa.listwttl;

public class Element {

    private String ip;
    private long heartbeat;

    public Element(String ip) {
        this.ip = ip;
        this.heartbeat = System.currentTimeMillis();
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public long getHeartbeat() {
        return heartbeat;
    }

    public void setHeartbeat(long heartbeat) {
        this.heartbeat = heartbeat;
    }

    public void updateHearthbeat(){
        this.heartbeat = System.currentTimeMillis();
    }


}
