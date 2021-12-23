package com.example;

import java.net.URL;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class Commons implements Runnable {
    private static final Log logger = org.apache.commons.logging.LogFactory.getLog(Commons.class);

    @Override
    public void run() {
        logger.info("log by " + logger.getClass());

        final URL resource = logger.getClass().getResource(
                '/' + logger.getClass().getName().replace('.', '/') + ".class");

        logger.info("logger class jar: " + resource);
    }
}
