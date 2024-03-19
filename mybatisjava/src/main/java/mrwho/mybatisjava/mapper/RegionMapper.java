package mrwho.mybatisjava.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import mrwho.mybatisjava.entity.Region;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface RegionMapper extends BaseMapper<Region> {
    
    int batchInsert(@Param("list")List<Region> list);
    int insertList(@Param("list")List<Region> list);

	
}