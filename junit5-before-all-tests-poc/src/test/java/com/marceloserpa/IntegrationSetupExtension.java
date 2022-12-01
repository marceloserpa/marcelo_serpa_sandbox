package com.marceloserpa;


import org.junit.jupiter.api.extension.BeforeAllCallback;
        import org.junit.jupiter.api.extension.ExtensionContext;
        import static org.junit.jupiter.api.extension.ExtensionContext.Namespace.GLOBAL;

public class IntegrationSetupExtension implements BeforeAllCallback, ExtensionContext.Store.CloseableResource {

    private static boolean started = false;

    @Override
    public void beforeAll(ExtensionContext context) {
        if (!started) {
            started = true;
            // Your "before all tests" startup logic goes here
            // The following line registers a callback hook when the root test context is shut down
            context.getRoot().getStore(GLOBAL).put("any unique name", this);
            new FakeDB().save(new Book(1L, "The Shining", "Stephen King"));
        }
    }

    @Override
    public void close() {
        // Your "after all tests" logic goes here
    }
}