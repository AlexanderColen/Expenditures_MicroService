package com.fontys.capitaselecta.rest;

import com.fontys.capitaselecta.domain.Greeting;
import java.util.concurrent.atomic.AtomicInteger;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Alex
 */
@RestController
public class GreetingsController {
    private static final String GREETING_TEMPLATE = "Hello %s";
    private final AtomicInteger counter = new AtomicInteger();
    
    @RequestMapping(value = "/greeting")
    public Greeting greet(@RequestParam(value = "name", defaultValue="World") String name) {
        return new Greeting(counter.getAndIncrement(), String.format(GREETING_TEMPLATE, name));
    }
}