import jakarta.annotation.Resource;
import mrwho.mybatis.MybatisApplication;
import mrwho.mybatis.entity.User;
import mrwho.mybatis.entity.Users;
import mrwho.mybatis.mapper.UserMapper;
import mrwho.mybatis.mapper.UsersMapper;
import mrwho.mybatis.service.SomeService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest(classes = {MybatisApplication.class})
public class MybatisTest {
    
    @Resource
    private UserMapper userMapper;
    @Resource
    private UsersMapper usersMapper;

    
    @Test
    void testUserMapper() {
        List<User> users = userMapper.findByAll(new User().setId(1L));
        users.forEach(System.out::println);
        List<Users> users1 = usersMapper.selectByAll(new Users());
        List<User> users2 = userMapper.find();
        users1.forEach(System.out::println);
    }

}
