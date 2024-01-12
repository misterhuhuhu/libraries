package mrwho.mybatis.mapper;

import java.util.List;

import mrwho.mybatis.entity.User;

public interface UserMapper {
    int deleteByPrimaryKey(Long id);
    
    List<User> findByAll(User user);

	
    int insert(User record);
    
    int insertSelective(User record);
    
    User selectByPrimaryKey(Long id);
    
    int updateByPrimaryKeySelective(User record);
    
    int updateByPrimaryKey(User record);
}