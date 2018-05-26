package com.kryszak.module;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.kryszak.service.HelloService;

public class InstanceInjector {

    private InstanceInjector() {
        // static
    }

    private static Injector injector = Guice.createInjector(new ApplicationModule());

    public static HelloService getHelloService() {
        return injector.getInstance(HelloService.class);
    }
}
