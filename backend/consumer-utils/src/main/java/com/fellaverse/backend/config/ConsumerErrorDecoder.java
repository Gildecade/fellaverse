package com.fellaverse.backend.config;

import com.fellaverse.backend.exception.ConsumerException;
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
        log.error("Feign client error,response code is {}:",response.status());
        Exception exception;
        try {
            String json = Util.toString(response.body().asReader(StandardCharsets.UTF_8));
            log.error("Response body: "+ json);
            // DIY exception
            exception = new ConsumerException(response.status(), json);
        } catch (IOException ex) {
            log.error(ex.getMessage(), ex);
            exception = new RuntimeException("Unable to decode error message.");
        }
        return exception;
    }
}