package com.mybank.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {
    @GetMapping("/home")
            public String home()
    {
        return "Welcome to My Bank";
    }

    @GetMapping("/about")
    public String about()
    {
        return "Welcome to about My Bank";
    }

    @GetMapping("/contact")
    public String contact()
    {
        return "Welcome to contact My Bank";
    }

    @GetMapping("/contact/1")
    public String contact1()
    {
        return "Welcome to contact1 My Bank";
    }
}
