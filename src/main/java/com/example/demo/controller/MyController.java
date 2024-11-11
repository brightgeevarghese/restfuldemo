package com.example.demo.controller;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class MyController {
    @GetMapping("/hello")
    public String sayHello() {
        return "Hello World!";
    }

//    @GetMapping("/say-hello")
//    public ResponseEntity<String> sayHello2() {
//        return new ResponseEntity<>(
//                "Hello",
//                HttpStatusCode.valueOf(200)
//        );
//    }
    @GetMapping("/say-hello2")
    public ResponseEntity<String> sayHello2() {
        return ResponseEntity.status(200).body("Hello World!");
    }
//@GetMapping("/say-hello")
//public ResponseEntity<String> sayHello2() {
//    return ResponseEntity.accepted().build();
//}
//    @GetMapping("/say-hello")
//    public ResponseEntity<String> sayHello2() {
//        HttpHeaders headers = new HttpHeaders();
//        headers.add(HttpHeaders.ACCEPT, "image/png");
//        headers.add("Custom Header", "My Value");
//        return ResponseEntity.accepted()
//                .headers(headers)
//                .build();
//    }

    @GetMapping("/hello/{name}")
    public String sayHello(@PathVariable String name) {
        return "Hello " + name;
    }

    @GetMapping("/hello/{firstname}/{lastname}")
    public String sayHello( @PathVariable String firstname, @PathVariable String lastname) {
        return "Hello " + firstname + " " + lastname;
    }
}
