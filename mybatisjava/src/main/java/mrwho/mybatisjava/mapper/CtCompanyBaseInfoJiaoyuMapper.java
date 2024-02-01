package mrwho.mybatisjava.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import mrwho.mybatisjava.entity.CtCompanyBaseInfoJiaoyu;

import java.util.List;

public interface CtCompanyBaseInfoJiaoyuMapper extends BaseMapper<CtCompanyBaseInfoJiaoyu> {
    List<CtCompanyBaseInfoJiaoyu> selectCT_COMPANY_BASE_INFO_JIAOYU();
}