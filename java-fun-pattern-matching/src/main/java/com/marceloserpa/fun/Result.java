package com.marceloserpa.fun;

public interface Result<T> {

    record Success<T>(T t) implements Result<T> {

    }

    record Failed<T>(String message) implements Result<T> {

    }

}
