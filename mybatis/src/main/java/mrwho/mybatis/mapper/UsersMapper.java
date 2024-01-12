package mrwho.mybatis.mapper;

import java.util.List;

import mrwho.mybatis.entity.Users;

public interface UsersMapper {
    int deleteByPrimaryKey(String username);
    
    int insert(Users record);
    List<Users> selectByAll(Users users);

	
    int insertSelective(Users record);
    
    Users selectByPrimaryKey(String username);
    
    int updateByPrimaryKeySelective(Users record);
    
    int updateByPrimaryKey(Users record);
}