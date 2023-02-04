package com.fellaverse.backend.config;

import feign.Response;
import feign.Util;
import feign.codec.ErrorDecoder;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

@Slf4j
public class ConsumerErrorDecoder implements ErrorDecoder {
    @Override
    public Exception decode(String s, Response response) {
        Exception exception;
        try {
            String json = Util.toString(response.body().asReader(StandardCharsets.UTF_8));
            // DIY exception
            exception = new RuntimeException(json);
        } catch (IOException ex) {
            log.error(ex.getMessage(), ex);
            exception = new RuntimeException("Unable to decode error message.");
        }
        return exception;
    }
}