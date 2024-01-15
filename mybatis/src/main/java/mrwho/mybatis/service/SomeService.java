package mrwho.mybatis.service;

import org.springframework.stereotype.Component;

@Component
public class SomeService {
    
    public String SomeMethod() {
        return PrivateMethod();
    }
    
    private String PrivateMethod() {
        return "hello";
    }
    
    public String SomeMethodWithArgs(String args) {
        return "SomeMethodWithArgs";
    }
}
