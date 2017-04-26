package com.bookmanagement.log;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component("LoggingUtil")
public class LoggingUtil implements Logging {

    public static final String BOOK_HANDLE_LOGGER = "book_handle.logger";
    private static Logger bookHandleLogger = LoggerFactory.getLogger(BOOK_HANDLE_LOGGER);

    @Override
    public Logger getBookHandleLogger() {
        return bookHandleLogger;
    }

    public boolean isDebugMode() {
        boolean isDebug = java.lang.management.ManagementFactory.getRuntimeMXBean().getInputArguments().toString().indexOf("jdwp") >= 0;
        return isDebug;
    }
}
