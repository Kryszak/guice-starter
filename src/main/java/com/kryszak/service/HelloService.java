package com.kryszak.service;

import com.kryszak.module.logging.LogMethodCall;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class HelloService {

    @LogMethodCall
    public void hello() {
        log.info("Hello!");
    }
}
