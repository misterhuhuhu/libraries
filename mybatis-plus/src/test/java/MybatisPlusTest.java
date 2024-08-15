import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.override.MybatisMapperProxy;
import jakarta.annotation.Resource;

import lombok.extern.slf4j.Slf4j;
import mrwho.mybatisplus.MybatisplusApplication;
import mrwho.mybatisplus.entity.BladeParam;
import mrwho.mybatisplus.mapper.BladeParamMapper;
import mrwho.mybatisplus.service.BladeParamService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;
import java.util.List;


@SpringBootTest(classes = {MybatisplusApplication.class})
@Slf4j
public class MybatisPlusTest {
    
    @Resource
    private BladeParamMapper bladeParamMapper;
    @Resource
    private BladeParamService bladeParamService;

    
    @Test
    void testMapper() {
        MybatisMapperProxy invocationHandler = (MybatisMapperProxy)Proxy.getInvocationHandler(bladeParamMapper);
        
        List<BladeParam> bladeParams = bladeParamMapper.selectList(new LambdaQueryWrapper<BladeParam>().eq(BladeParam::getParamKey,"1"));
        List<BladeParam> list = bladeParamService.list();
//        log.info(bladeParams.toString());
//        log.info(list.toString());
//
        
    }
    

}
