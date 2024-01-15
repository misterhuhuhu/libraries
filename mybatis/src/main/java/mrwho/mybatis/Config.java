package mrwho.mybatis;

import lombok.extern.slf4j.Slf4j;
import mrwho.mybatis.plugins.Plugin3;
import mrwho.mybatis.plugins.SQLLogPlugin;
import mrwho.mybatis.plugins.SQLLogPlugin2;
import org.mybatis.spring.boot.autoconfigure.ConfigurationCustomizer;
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
            configuration.addInterceptor(new Plugin3());
            
        };
    }
    
}