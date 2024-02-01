package mrwho.mybatisjava.service;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import mrwho.mybatisjava.entity.CtCompanyInfoJiaoyu;
import mrwho.mybatisjava.mapper.CtCompanyInfoJiaoyuMapper;

import java.util.Collection;

@Service
public class CtCompanyInfoJiaoyuService extends ServiceImpl<CtCompanyInfoJiaoyuMapper, CtCompanyInfoJiaoyu> {
    
    @Override
    @Async
    public boolean saveBatch(Collection<CtCompanyInfoJiaoyu> entityList) {
        return super.saveBatch(entityList);
    }
}
