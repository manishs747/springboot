package com.base.utils;

import lombok.extern.slf4j.Slf4j;
import okhttp3.logging.HttpLoggingInterceptor;

@Slf4j
public class HTTPLogger implements HttpLoggingInterceptor.Logger {

    public static final HTTPLogger DEFAULT = new HTTPLogger();

    private HTTPLogger() {
    }

    public void log(String message) {
        log.info(message);
    }

}
