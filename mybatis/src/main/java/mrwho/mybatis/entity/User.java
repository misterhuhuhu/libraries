package mrwho.mybatis.entity;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@ToString

public class User {
    private Long id;
    
    private Integer age;
    
    private String name;
    
    public Long getId() {
        return id;
    }
    
    public User setId(Long id) {
        this.id = id;
        return this;
    }
    
    public Integer getAge() {
        return age;
    }
    
    public User setAge(Integer age) {
        this.age = age;
        return this;
    }
    
    public String getName() {
        return name;
    }
    
    public User setName(String name) {
        this.name = name;
        return this;
    }
}