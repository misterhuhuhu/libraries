package sample.web.application.controller;

import jakarta.annotation.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import sample.web.application.service.HelloService;


@RestController
public class HelloController {
    public static final Logger LOGGER = LoggerFactory.getLogger(HelloController.class);

    @Resource
    private HelloService helloService;
    @GetMapping("/hello")
    public String Hello(){
        return helloService.Hello();
    }
}
