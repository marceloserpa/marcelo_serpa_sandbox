package com.marceloserpa.dop.optional;

public class Application {

    public static void main(String[] args) {

        Opt<String> opt1 = new Opt.Some<String>("Marcelo");
        Opt<String> opt2 = new Opt.None<String>();
        System.out.println(greetings(opt1));
        System.out.println(greetings(opt2));
    }

    private static<T> String greetings(Opt<T> opt) {
        return switch (opt) {
            case Opt.Some<T>(var text) -> "welcome " + text;
            case Opt.None<T>()  -> "Not found";
        };
    }


}
