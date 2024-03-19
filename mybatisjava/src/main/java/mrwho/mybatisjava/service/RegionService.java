package mrwho.mybatisjava.service;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import mrwho.mybatisjava.entity.Region;
import mrwho.mybatisjava.mapper.RegionMapper;

import java.util.Collection;

@Service
public class RegionService extends ServiceImpl<RegionMapper, Region> {
    
    @Override
    public boolean saveBatch(Collection<Region> entityList) {
        return super.saveBatch(entityList);
    }
}
