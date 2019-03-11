package com.epam;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class App {

    private static final Logger logger = LogManager.getLogger(App.class);

    public static void main(String[] args) {
        for (int i = 0; i < 5; i++) {
            logger.info("info: " + i);
            logger.error("error: " + i);
            logger.warn("warn: " + i);
            logger.debug("debug: " + i);
        }

        SomeClass someClass = new SomeClass();
        someClass.method();
    }
}
