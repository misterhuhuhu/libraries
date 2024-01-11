package bytebuddy;

import net.bytebuddy.agent.builder.AgentBuilder;
import net.bytebuddy.description.type.TypeDescription;
import net.bytebuddy.dynamic.DynamicType;
import net.bytebuddy.utility.JavaModule;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CustomerListener implements AgentBuilder.Listener {
    public static final Logger LOGGER = LoggerFactory.getLogger(CustomerListener.class);
    @Override
    public void onDiscovery(String typeName, ClassLoader classLoader, JavaModule module, boolean loaded) {
        LOGGER.info("onDiscovery  {}",typeName);
    }
    
    @Override
    public void onTransformation(TypeDescription typeDescription, ClassLoader classLoader, JavaModule module, boolean loaded, DynamicType dynamicType) {
        LOGGER.info("onTransformation {}",typeDescription.getActualName());
    }
    
    @Override
    public void onIgnored(TypeDescription typeDescription, ClassLoader classLoader, JavaModule module, boolean loaded) {
        LOGGER.info("onIgnored {} " ,typeDescription.getActualName());
        
    }
    
    @Override
    public void onError(String typeName, ClassLoader classLoader, JavaModule module, boolean loaded, Throwable throwable) {
        LOGGER.info("onError {}",typeName);
        
    }
    
    @Override
    public void onComplete(String typeName, ClassLoader classLoader, JavaModule module, boolean loaded) {
        LOGGER.info("onComplete {}" ,typeName);
        
    }
}
