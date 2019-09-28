package com.example;

import java.net.URL;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;

public class Slf4jLog implements Runnable {
    private static final Logger logger = LoggerFactory.getLogger(Slf4jLog.class);

    @Override
    public void run() {
        MDC.put("logger", logger.getClass().getName());

        logger.info("log by {}", logger.getClass());

        final URL resource = logger.getClass().getResource(
                "/" + logger.getClass().getName().replace('.', '/') + ".class");

        logger.info("logger class jar: {}", resource);

        MDC.clear();
    }
}
