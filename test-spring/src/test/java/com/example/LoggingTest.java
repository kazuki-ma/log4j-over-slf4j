package com.example;

import org.junit.Test;
import org.slf4j.bridge.SLF4JBridgeHandler;

public class LoggingTest {
    static {
        SLF4JBridgeHandler.removeHandlersForRootLogger();
        SLF4JBridgeHandler.install();
    }

    @Test
    public void logging() {
        new JDK().run();
        new Commons().run();
        new Log4j1().run();
        new Log4j2().run();
        new Slf4jLog().run();
    }
}
