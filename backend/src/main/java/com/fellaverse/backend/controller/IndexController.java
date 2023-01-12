package com.fellaverse.backend.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller  // declare it is a spring controller; must
public class IndexController {

    @GetMapping(value={"/","/index"})  // only handle request with get method, routes are "/" and "/index"
    @ResponseBody  // return a String in json format; if you want to return a page, remove this annotation
    public String index() {
        return "Hello, fellas";
    }
}
