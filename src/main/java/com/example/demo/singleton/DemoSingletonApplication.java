package com.example.demo.singleton;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
public class DemoSingletonApplication {
    public static void main(String[] args) {
        SpringApplication.run(DemoSingletonApplication.class, args);
    }
}

@Component
class SharedSingleton {

    private String value = "initial-value";

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}

@Service
class Service1 {

    @Autowired
    private SharedSingleton singleton;

    void method() {
        System.out.println("Current value of singleton: " + singleton.getValue());

        singleton.setValue("service1.value");
    }
}

@Service
class Service2 {

    @Autowired
    private SharedSingleton singleton;

    void method() {
        System.out.println("Current value of singleton: " + singleton.getValue());

        singleton.setValue("service2.value");
    }
}

@RestController
class Api {

    @Autowired
    private Service1 service1;

    @Autowired
    private Service2 service2;

    @GetMapping("/method1")
    void method1() {
        service1.method();
    }

    @GetMapping("/method2")
    void method2() {
        service2.method();
    }
}