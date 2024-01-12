package mrwho.mybatis;


import jakarta.annotation.Resource;
import mrwho.mybatis.entity.User;
import mrwho.mybatis.entity.Users;
import mrwho.mybatis.mapper.UserMapper;
import mrwho.mybatis.mapper.UsersMapper;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.util.List;

@SpringBootApplication
@MapperScan({"mrwho.mybatis.mapper"})
public class MybatisApplication {

    
    
    public static void main(String[] args) {
        ConfigurableApplicationContext run = SpringApplication.run(MybatisApplication.class, args);
        UserMapper userMapper = run.getBean(UserMapper.class);
        UsersMapper usersMapper = run.getBean(UsersMapper.class);
        
        List<User> users = userMapper.findByAll(new User().setId(1L));
        List<User> users2 = userMapper.find();
        users.forEach(System.out::println);
        List<Users> users1 = usersMapper.selectByAll(new Users());
        users1.forEach(System.out::println);
    }
}
