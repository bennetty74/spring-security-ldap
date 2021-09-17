package com.bennetty74.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author bennetty74
 * @date 2021.9.12
 */
@RestController
public class IndexController {

    @GetMapping("/")
    public String hello(){
        return "Hello Spring Security";
    }
}
