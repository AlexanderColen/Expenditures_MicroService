package com.fontys.capitaselecta.rest;

import com.fontys.capitaselecta.domain.Greeting;
import java.util.concurrent.atomic.AtomicInteger;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Alex
 */
@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping(value = "/greeting")
public class GreetingsController {
    private static final String GREETING_TEMPLATE = "Hello %s";
    private final AtomicInteger counter = new AtomicInteger();
    
    @RequestMapping(value = "/{name}", method = RequestMethod.GET)
    public Greeting greet(@PathVariable("name") String name) {
        return new Greeting(counter.getAndIncrement(), String.format(GREETING_TEMPLATE, name));
    }
}