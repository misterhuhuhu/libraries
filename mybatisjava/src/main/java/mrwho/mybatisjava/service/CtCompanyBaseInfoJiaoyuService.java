package mrwho.mybatisjava.service;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import mrwho.mybatisjava.entity.CtCompanyBaseInfoJiaoyu;
import mrwho.mybatisjava.mapper.CtCompanyBaseInfoJiaoyuMapper;

import java.util.Collection;

@Service
public class CtCompanyBaseInfoJiaoyuService extends ServiceImpl<CtCompanyBaseInfoJiaoyuMapper, CtCompanyBaseInfoJiaoyu> {
    @Override
    @Async
    public boolean saveBatch(Collection<CtCompanyBaseInfoJiaoyu> entityList) {
        return super.saveBatch(entityList);
    }
}
