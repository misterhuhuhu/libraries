package mrwho.mybatisplus.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import mrwho.mybatisplus.entity.BladeParam;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BladeParamMapper extends BaseMapper<BladeParam> {
    /**
     * 使用 queryWrapper.customSqlSegment 在 xml 里使用 Wrapper
     *
     * @param bladeParam
     * @param queryWrapper
     * @return
     */
    List<BladeParam> selectByAll(BladeParam bladeParam, @Param("queryWrapper") Wrapper<?> queryWrapper);
}