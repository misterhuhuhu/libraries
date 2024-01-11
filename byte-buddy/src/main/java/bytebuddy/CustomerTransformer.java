package bytebuddy;

import net.bytebuddy.agent.builder.AgentBuilder;
import net.bytebuddy.description.type.TypeDescription;
import net.bytebuddy.dynamic.DynamicType;
import net.bytebuddy.implementation.MethodDelegation;
import net.bytebuddy.matcher.ElementMatchers;
import net.bytebuddy.utility.JavaModule;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.security.ProtectionDomain;

public class CustomerTransformer implements AgentBuilder.Transformer {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(CustomerTransformer.class);
    @Override
    public DynamicType.Builder<?> transform(DynamicType.Builder<?> builder, TypeDescription typeDescription, ClassLoader classLoader, JavaModule module, ProtectionDomain protectionDomain) {
        
        LOGGER.info("typeDescription.getActualName() = {}",typeDescription.getActualName());
        DynamicType.Builder.MethodDefinition.ReceiverTypeDefinition<?> intercept = builder.method(ElementMatchers.not(ElementMatchers.isStatic())).intercept(MethodDelegation.to(new SpringMvcFoo5()));
        return intercept;
    }
}
