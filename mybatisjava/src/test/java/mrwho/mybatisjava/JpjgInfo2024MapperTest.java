package mrwho.mybatisjava;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import mrwho.mybatisjava.entity.JpjgInfo2024;
import mrwho.mybatisjava.entity.JpjgInfoGen;
import mrwho.mybatisjava.mapper.JpjgInfo2024Mapper;
import mrwho.mybatisjava.service.JpjgInfoGenService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.BeanUtils;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest(classes = MybatisplusApplication.class)
@Slf4j
public class JpjgInfo2024MapperTest {
    @Resource
    JpjgInfo2024Mapper jpjgInfo2024Mapper;
    @Resource
    JpjgInfoGenService jpjgInfoGenService;
    
    @Test
    public void SelectAndInsert() {
        List<JpjgInfo2024> jpjgInfo2024s = jpjgInfo2024Mapper.selectByAll(null, new LambdaQueryWrapper<JpjgInfo2024>().eq(JpjgInfo2024::getId,"sadfasdf"));
        
        List<JpjgInfoGen> collect = jpjgInfo2024s.stream().map(item -> {
            item.setId(null);
            JpjgInfoGen jpjgInfoGen = new JpjgInfoGen();
            BeanUtils.copyProperties(item, jpjgInfoGen);
            return jpjgInfoGen;
        }).toList();
//        boolean b = jpjgInfoGenService.saveBatch(collect, 2500);
        log.info(String.valueOf(jpjgInfo2024s.size()));
    }
}
