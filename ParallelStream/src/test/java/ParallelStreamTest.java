import ParallelStream.MybatisplusApplication;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.util.ObjectUtils;

import java.util.List;
import java.util.stream.IntStream;

@SpringBootTest(classes = MybatisplusApplication.class)
@Slf4j
public class ParallelStreamTest {
    private static final ThreadLocal<String> localVar = new ThreadLocal<>();
    @Resource
    private ThreadPoolTaskExecutor threadPoolTaskExecutor;
    
    @Test
    public void dummy() {
        List<Integer> collect = IntStream.range(0, 11111).boxed().toList();
        
        threadPoolTaskExecutor.submit(() -> collect.parallelStream().forEach(k ->
        {
            String i = localVar.get();
            if (ObjectUtils.isEmpty(i)) {
                localVar.set(Thread.currentThread().getName());
                log.info("当前 {},设置{}", i, Thread.currentThread().getName());
            } else {
                log.info("当前 {},获得{}", Thread.currentThread().getName(), i);
            }
            
        }));
//        collect.parallelStream().forEach(k->log.info(Integer.toString(k)));
        
    }
}
