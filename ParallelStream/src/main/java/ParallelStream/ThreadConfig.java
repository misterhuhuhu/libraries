package ParallelStream;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.CustomizableThreadFactory;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.ThreadPoolExecutor;

@Configuration
@Slf4j
public class ThreadConfig {
    
    @Bean
    public ThreadPoolTaskExecutor threadPoolTaskExecutor() {
        
        int cores = Runtime.getRuntime().availableProcessors();
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(cores - 1);
        executor.setMaxPoolSize(cores * 2);
        executor.setKeepAliveSeconds(60);
        executor.setQueueCapacity(100000);
       
        executor.setTaskDecorator((runnable) -> {
            try {
                log.info("thread name {}", Thread.currentThread().getName());
                return runnable;
            } catch (IllegalStateException e) {
                return runnable;
            }
        });
        executor.setAllowCoreThreadTimeOut(true);
        executor.afterPropertiesSet();
        return executor;
    }
}
