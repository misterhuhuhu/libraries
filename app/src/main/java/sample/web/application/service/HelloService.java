package sample.web.application.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class HelloService {
    public static final Logger LOGGER = LoggerFactory.getLogger(HelloService.class);
    
    public String Hello() {
        return "hello";
    }
}
