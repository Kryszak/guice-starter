package com.kryszak;

import com.kryszak.module.InstanceInjector;
import com.kryszak.service.HelloService;

public class Main {

    public static void main(String[] args) {
        HelloService service = InstanceInjector.getHelloService();
        service.hello();
    }
}
