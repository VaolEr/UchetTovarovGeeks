package ru.ValolEr.serverApi;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HelloController {

    @GetMapping("/test")
    public String showTest(){
        return "test";
    }

    @GetMapping("/hello-world")
    public String sayHello(){
        return "hello_world";
    }

}
