package mrwho.mybatisplus;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import mrwho.mybatisplus.entity.BladeParam;
import mrwho.mybatisplus.mapper.BladeParamMapper;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.util.List;

@SpringBootApplication
@MapperScan({"mrwho.*.mapper"})
public class MybatisplusApplication {
    
    public static void main(String[] args) {
        ConfigurableApplicationContext run = SpringApplication.run(MybatisplusApplication.class, args);
        
        BladeParamMapper bean = run.getBean(BladeParamMapper.class);
        List<BladeParam> bladeParams = bean.selectByAll();
        System.out.print(bladeParams);
    }
}
