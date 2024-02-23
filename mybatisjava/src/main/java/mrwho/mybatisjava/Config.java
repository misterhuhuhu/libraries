package mrwho.mybatisjava;

import com.baomidou.mybatisplus.autoconfigure.ConfigurationCustomizer;
import lombok.extern.slf4j.Slf4j;

import mrwho.mybatisjava.plugins.SQLLogPlugin;
import mrwho.mybatisjava.plugins.SQLLogPlugin2;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@Slf4j
public class Config {
    
    @Bean
    public ConfigurationCustomizer configurationCustomizer() {
        
        return configuration -> {
            configuration.addInterceptor(new SQLLogPlugin());
            configuration.addInterceptor(new SQLLogPlugin2());
        };
    }
    
}