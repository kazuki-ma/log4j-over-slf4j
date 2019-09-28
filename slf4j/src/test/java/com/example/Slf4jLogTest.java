package com.example;

import org.junit.Test;

public class Slf4jLogTest {
    @Test
    public void logging() {
        new Slf4jLog().run();
    }
}
