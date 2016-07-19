package com.mnubo.java.sdk.client;

public interface Consumer<T> {
    void accept(T t);
}
