package mrwho.mybatisjava.service;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import mrwho.mybatisjava.entity.Regiontbl;
import mrwho.mybatisjava.mapper.RegiontblMapper;

import java.util.Collection;

@Service
public class RegiontblService extends ServiceImpl<RegiontblMapper, Regiontbl> {
    
    @Override
    @Async
    public boolean saveBatch(Collection<Regiontbl> entityList, int batchSize) {
        return super.saveBatch(entityList, batchSize);
    }
}
