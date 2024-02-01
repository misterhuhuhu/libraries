package mrwho.mybatisjava.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import mrwho.mybatisjava.entity.SupMerchantNodeTbl;
import mrwho.mybatisjava.entity.SupMerchantNodeTblWithRegion;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SupMerchantNodeTblMapper extends BaseMapper<SupMerchantNodeTbl> {
    
    
    List<SupMerchantNodeTblWithRegion> selectSupMerchantNodeTblWithRegion();
    
    
    Long selectSupMerchantNodeTblCount();
    
    List<SupMerchantNodeTblWithRegion> selectSupMerchantNodeTblWithRegionByLimit(@Param("start") int start, @Param("offset") int offset);
}