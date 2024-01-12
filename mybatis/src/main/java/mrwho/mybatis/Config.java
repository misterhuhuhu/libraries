package mrwho.mybatis;

import lombok.extern.slf4j.Slf4j;
import mrwho.mybatis.plugins.SQLLogPlugin;
import org.mybatis.spring.boot.autoconfigure.ConfigurationCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@Slf4j
public class Config {
    
    @Bean
    public ConfigurationCustomizer configurationCustomizer() {
        
        return configuration -> configuration.addInterceptor(new SQLLogPlugin());
    }
    
}