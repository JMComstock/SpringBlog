package com.codeup.springblog.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class HelloController {

//    @GetMapping("/hello")
//    @ResponseBody
//    public String hello() {
//
//        return "Hello from Spring! This is the new controller!";
//    }
//
//    @RequestMapping(path="/hello/{name}/and/{age}", method = RequestMethod.GET)
//    @ResponseBody
//    public String helloNameAge(@PathVariable String name, @PathVariable int age) {
//
//        return "Hey user! Thanks for letting me know your name is " + name + ". You also told me you are " + age + " years old.";
//    }


    @GetMapping("/hello/{name}")
    public String sayHello(@PathVariable String name, Model model) {

        model.addAttribute("enteredName", name);
        return "hello";
    }

}
