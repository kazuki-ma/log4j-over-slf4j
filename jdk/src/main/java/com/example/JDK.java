package com.example;

import java.net.URL;
import java.util.logging.Logger;

public class JDK implements Runnable {
    private static final Logger logger = java.util.logging.Logger.getLogger(JDK.class.getName());

    @Override
    public void run() {
        logger.info("log by " + logger.getClass());

        final URL resource = logger.getClass().getResource(
                "/" + logger.getClass().getName().replace('.', '/') + ".class");

        logger.info("logger class jar: " + resource);
    }
}
