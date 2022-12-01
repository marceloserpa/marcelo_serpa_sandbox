package com.marceloserpa;


import org.junit.jupiter.api.extension.BeforeAllCallback;
        import org.junit.jupiter.api.extension.ExtensionContext;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import static org.junit.jupiter.api.extension.ExtensionContext.Namespace.GLOBAL;

public class IntegrationSetupExtension implements BeforeAllCallback, ExtensionContext.Store.CloseableResource {

    private static final Lock LOCK = new ReentrantLock();
    private static volatile boolean started = false;

    @Override
    public void beforeAll(ExtensionContext context) {
        LOCK.lock();
        try {
            if (!started) {
                started = true;
                // Your "before all tests" startup logic goes here
                // The following line registers a callback hook when the root test context is shut down
                context.getRoot().getStore(GLOBAL).put("any unique name", this);
                new FakeDB().save(new Book(1L, "The Shining", "Stephen King"));
            }
        } finally {
            LOCK.unlock();
        }
    }

    @Override
    public void close() {
        // Your "after all tests" logic goes here
    }
}