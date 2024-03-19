package mrwho.mybatisjava.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import mrwho.mybatisjava.entity.JpjgInfo2024;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface JpjgInfo2024Mapper extends BaseMapper<JpjgInfo2024> {
    List<JpjgInfo2024> selectByAll(@Param("jpjgInfo2024") JpjgInfo2024 jpjgInfo2024,@Param(Constants.WRAPPER) Wrapper<?> queryWrapper);
    
    List<JpjgInfo2024> select20240226();
}