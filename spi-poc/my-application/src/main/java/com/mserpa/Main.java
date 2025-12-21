package com.mserpa;

import com.mserpa.rate.api.Logger;

import java.util.ServiceLoader;

public class Main {
    static void main() {
        IO.println(String.format("Hello and welcome!"));

        ServiceLoader<Logger> loader = ServiceLoader.load(Logger.class);

        loader.stream().forEach(provider -> provider.get().log("oiiiii"));

    }
}
