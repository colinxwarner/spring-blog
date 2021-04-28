package com.test.springtest.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class HelloController {

    @GetMapping("/hello")
    @ResponseBody
    public String hello() {

        return "Hello from Spring";
    }

    @GetMapping("/hello/{Coco}")
    @ResponseBody
    public String hello(@PathVariable String Coco) {
        return "Hola " + Coco + "!";
    }

    @RequestMapping(path = "/increment/{number}", method = RequestMethod.GET)
    @ResponseBody
    public String addOne(@PathVariable int number) {
        return number + " plus one is " + (number + 1);
    }
}
