package com.example;

import java.net.URL;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.ThreadContext;

public class Log4j2 implements Runnable {
    private static final Logger logger = org.apache.logging.log4j.LogManager.getLogger();

    @Override
    public void run() {
        ThreadContext.put("logger", logger.getClass().getName());

        logger.info("log by {}", logger.getClass());

        final URL resource = logger.getClass().getResource(
                "/" + logger.getClass().getName().replace('.', '/') + ".class");

        logger.info("logger class jar: {}", resource);

        ThreadContext.clearAll();
    }
}
