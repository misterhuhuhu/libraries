package mrwho.mybatisjava.plugins;

import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Component;

@Component
public class ThreadPoolConfig {
    @Bean
    public ThreadPoolTaskExecutor threadPoolTaskExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(6);
        executor.setMaxPoolSize(6);
        executor.setKeepAliveSeconds(600);
        executor.setQueueCapacity(100000);
        executor.setAllowCoreThreadTimeOut(true);
        return executor;
    }
}
