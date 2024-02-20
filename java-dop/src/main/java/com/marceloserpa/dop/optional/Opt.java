package com.marceloserpa.dop.optional;

sealed interface Opt<T> {

    record Some<T>(T value) implements Opt<T> { }
    record None<T>() implements Opt<T> { }
}
