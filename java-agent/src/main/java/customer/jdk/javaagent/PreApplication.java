package customer.jdk.javaagent;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.instrument.Instrumentation;

/**
 * -javaagent:D:\dev_project\libraries\java-agent\target\java-agent-0.0.1-SNAPSHOT-jar-with-dependencies.jar=k1=v1,k2=v2  -jar ***.jar
 *
 *  使用原生jdk 注入 固定方法名
 *
 * */
public class PreApplication {
    public static final Logger logger = LoggerFactory.getLogger(PreApplication.class);
    
    public static void premain(String args, Instrumentation instrumentation) {
        
        logger.info("进入premain");
        logger.info("args {}", args);
        instrumentation.addTransformer(new CustomerClassFileTransformer());
        /**
         * Connected to the target VM, address: '127.0.0.1:3284', transport: 'socket'
         * 17:19:53.092 [main] INFO customer.jdk.javaagent.PreApplication -- 进入premain
         * 17:19:53.095 [main] INFO customer.jdk.javaagent.PreApplication -- args k1=v1,k2=v2
         * 17:19:53.097 [main] INFO customer.jdk.javaagent.CustomerClassFileTransformer -- className  : com/intellij/rt/debugger/agent/DebuggerAgent
         * 17:19:53.098 [main] INFO customer.jdk.javaagent.CustomerClassFileTransformer -- className  : com/intellij/rt/debugger/agent/CaptureAgent
         * 17:19:53.098 [main] INFO customer.jdk.javaagent.CustomerClassFileTransformer -- className  : com/intellij/rt/debugger/agent/CaptureAgent$ParamKeyProvider
         * 17:19:53.098 [main] INFO customer.jdk.javaagent.CustomerClassFileTransformer -- className  : com/intellij/rt/debugger/agent/CaptureAgent$KeyProvider
         * 17:19:53.098 [main] INFO customer.jdk.javaagent.CustomerClassFileTransformer -- className  : com/intellij/rt/debugger/agent/CaptureAgent$1
         * 17:19:53.098 [main] INFO customer.jdk.javaagent.CustomerClassFileTransformer -- className  : com/intellij/rt/debugger/agent/CaptureAgent$InstrumentPoint
         * 17:19:53.099 [main] INFO customer.jdk.javaagent.CustomerClassFileTransformer -- className  : com/intellij/rt/debugger/agent/CaptureAgent$FieldKeyProvider
         * 17:19:53.099 [main] INFO customer.jdk.javaagent.CustomerClassFileTransformer -- className  : org/jetbrains/capture/org/objectweb/asm/Type
         * 17:19:53.101 [main] INFO customer.jdk.javaagent.CustomerClassFileTransformer -- className  : com/intellij/rt/debugger/agent/CaptureAgent$CaptureTransformer
         * 17:19:53.101 [main] INFO customer.jdk.javaagent.CustomerClassFileTransformer -- className  : com/intellij/rt/debugger/agent/CaptureStorage
         * 17:19:53.101 [main] INFO customer.jdk.javaagent.CustomerClassFileTransformer -- className  : com/intellij/rt/debugger/agent/CaptureStorage$1
         * 17:19:53.102 [main] INFO customer.jdk.javaagent.CustomerClassFileTransformer -- className  : com/intellij/rt/debugger/agent/CollectionBreakpointInstrumentor
         * 17:19:53.102 [main] INFO customer.jdk.javaagent.CustomerClassFileTransformer -- className  : com/intellij/rt/debugger/agent/CollectionBreakpointInstrumentor$ConcurrentIdentityHashMap
         * 17:19:53.103 [main] INFO customer.jdk.javaagent.CustomerClassFileTransformer -- className  : com/intellij/rt/debugger/agent/CollectionBreakpointInstrumentor$KnownMethodsSet
         * 17:19:53.103 [main] INFO customer.jdk.javaagent.CustomerClassFileTransformer -- className  : com/intellij/rt/debugger/agent/CollectionBreakpointInstrumentor$ImmutableMethod
         * 17:19:53.103 [main] INFO customer.jdk.javaagent.CustomerClassFileTransformer -- className  : com/intellij/rt/debugger/agent/CollectionBreakpointInstrumentor$KnownMethod
         */
    }
}
