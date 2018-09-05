package com.github.marceloserpa.junitsuite.app;

public class DataBaseConfig {

    private static DataBaseConfig instance;

    private String ami;

    private DataBaseConfig(){

    }

    public static synchronized DataBaseConfig getInstance() {
        if(instance == null){
            instance = new DataBaseConfig();
        }
        return instance;
    }

    public String getAmi() {
        return ami;
    }

    public void setAmi(String ami) {
        this.ami = ami;
    }
}
