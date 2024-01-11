package bytebuddy;

import net.bytebuddy.agent.builder.AgentBuilder;
import net.bytebuddy.agent.builder.ResettableClassFileTransformer;
import net.bytebuddy.matcher.ElementMatchers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.instrument.Instrumentation;

public class InstancePreApplication {
    public static final Logger logger = LoggerFactory.getLogger(InstancePreApplication.class);
    
    /**
     * bytebuddy 注入
     * @param args
     * @param instrumentation
     * @throws ClassNotFoundException
     */
    public static void premain(String args, Instrumentation instrumentation) throws ClassNotFoundException {
        
        logger.info("进入premain");
        logger.info("args {}", args);
        ResettableClassFileTransformer resettableClassFileTransformer = new AgentBuilder.Default()
                                                                                .ignore(ElementMatchers.nameStartsWith("org.springframework")
                                                                                                .or(ElementMatchers.nameContains("net.bytebuddy")))
                                                                                .type(ElementMatchers.isAnnotatedWith(ElementMatchers.named("org.springframework.web.bind.annotation.RestController").or(ElementMatchers.named("org.springframework.web.bind.annotation.Controller"))))
                                                                                .transform(new CustomerTransformer()).with(new CustomerListener())
                                                                                .installOn(instrumentation);
    }
}