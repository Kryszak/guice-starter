package com.kryszak.module;

import com.google.inject.AbstractModule;
import com.google.inject.matcher.Matchers;
import com.kryszak.module.logging.LogMethodCall;
import com.kryszak.module.logging.LoggingInterceptor;
import com.kryszak.module.logging.TimeMethodCall;
import com.kryszak.module.logging.TimingInterceptor;
import lombok.extern.slf4j.Slf4j;

/**
 * Moduł odpowiadający za CDI
 */
@Slf4j
class ApplicationModule extends AbstractModule {

    @Override
    protected void configure() {
        bindInterceptor(Matchers.any(), Matchers.annotatedWith(LogMethodCall.class), new LoggingInterceptor());
        bindInterceptor(Matchers.any(), Matchers.annotatedWith(TimeMethodCall.class), new TimingInterceptor());
    }

}
