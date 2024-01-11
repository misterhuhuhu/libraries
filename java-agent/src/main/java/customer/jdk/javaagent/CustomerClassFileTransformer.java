package customer.jdk.javaagent;

import javassist.CannotCompileException;
import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtMethod;
import javassist.NotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.IllegalClassFormatException;
import java.security.ProtectionDomain;

public class CustomerClassFileTransformer implements ClassFileTransformer {
    public static final Logger LOGGER = LoggerFactory.getLogger(CustomerClassFileTransformer.class);
    
    /**
     *
     * @param loader                the defining loader of the class to be transformed,
     *                              may be {@code null} if the bootstrap loader
     * @param className             the name of the class in the internal form of fully
     *                              qualified class and interface names as defined in
     *                              <i>The Java Virtual Machine Specification</i>.
     *                              For example, <code>"java/util/List"</code>.
     * @param classBeingRedefined   if this is triggered by a redefine or retransform,
     *                              the class being redefined or retransformed;
     *                              if this is a class load, {@code null}
     * @param protectionDomain      the protection domain of the class being defined or redefined
     * @param classfileBuffer       the input byte buffer in class file format - must not be modified
     *
     * @return 不需要增强则返回null
     *          需要则返回字节码
     * @throws IllegalClassFormatException
     */
    @Override
    public byte[] transform(ClassLoader loader, String className, Class<?> classBeingRedefined, ProtectionDomain protectionDomain, byte[] classfileBuffer) throws IllegalClassFormatException {
        
        //加入日志功能
        String s = "sample.web.application.service.HelloService";
        byte[] byteCode = null;
        String targetClassName = s.replaceAll("\\.", "/"); //replace . with /
        //找到目标类
        if (!className.equals(targetClassName)) {
            return null;
        }
        LOGGER.info("className  : {}",className);
        LOGGER.info("[Agent] Transforming class MyAtm");
        try {
            ClassPool cp = ClassPool.getDefault();
            CtClass cc = cp.get(s);
            CtMethod m = cc.getDeclaredMethod("Hello");//找到目标方法
            m.addLocalVariable("startTime", CtClass.longType);
            m.insertBefore("startTime = System.currentTimeMillis();");

            StringBuilder endBlock = new StringBuilder();

            m.addLocalVariable("endTime", CtClass.longType);
            m.addLocalVariable("opTime", CtClass.longType);
            endBlock.append("endTime = System.currentTimeMillis();");
            endBlock.append("opTime = (endTime-startTime)/1000;");

            endBlock.append("LOGGER.info(\"[Application] Withdrawal operation completed in:\" + opTime + \" seconds!\");");

            m.insertAfter(endBlock.toString());
            m.insertBefore("LOGGER.info(\"已注入\");");
            LOGGER.info("已注入 className  : {}",className);
            byteCode = cc.toBytecode();
        } catch (NotFoundException | CannotCompileException | IOException e) {
            LOGGER.error("Exception", e);
        }
        return byteCode;
    }
}
