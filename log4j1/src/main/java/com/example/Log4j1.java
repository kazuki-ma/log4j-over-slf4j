package com.example;

import java.net.URL;

import org.apache.log4j.Logger;
import org.apache.log4j.NDC;

public class Log4j1 implements Runnable {
    private final Logger logger = org.apache.log4j.Logger.getLogger(Log4j1.class);

    @Override
    public void run() {
        NDC.push("NDC");

        logger.info("log by " + logger.getClass());

        final URL resource = logger.getClass().getResource(
                "/" + logger.getClass().getName().replace('.', '/') + ".class");

        logger.info("logger class jar: " + resource);
    }
}
