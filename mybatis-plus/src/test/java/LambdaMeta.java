import mrwho.mybatisplus.SComparator;
import mrwho.mybatisplus.SFunction;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serializable;
import java.lang.invoke.SerializedLambda;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.function.Function;
import java.util.stream.IntStream;

public class LambdaMeta {
    static Logger log = LoggerFactory.getLogger(LambdaMeta.class);
    @BeforeAll
    static void beforeAll() {
    
    }
    
    @Test
    void name() {
        String ss= "2";
        Function fn = (Function & Serializable) (n)
                                                        -> "Hello " + n;//定义一个lambda表达式
        SFunction<String,Integer> func1=(someString)-> Integer.valueOf(someString);
        SFunction<String,Object> func2=(someString)-> someString;
        SFunction<Long,String> func3=(someLong)-> {
            int a=2;
            return ss;
        };
        
        SComparator<String> func4 = (a,b)-> 4;
        SComparator<String> func5 = (a,b)->3;
//        getSerializedLambdaInfo(fn);
        func1.apply(ss);
//        getSerializedLambdaInfo(func1);
        getSerializedLambdaInfo(func3);
//        getSerializedLambdaInfo(func5);
    }
    private static void getSerializedLambdaInfo(final Object expression) {
        final Class<?> cl = expression.getClass();
        try {
            final Method m = cl.getDeclaredMethod("writeReplace");
            m.setAccessible(true);
            final Object result = m.invoke(expression);
            if (result instanceof SerializedLambda serializedLambda) {
                log.info(" Lambda FunctionalInterface: {}.{},MethodSignature:  ({})",
                        serializedLambda.getFunctionalInterfaceClass(),//定义的函数式接口
                        serializedLambda.getFunctionalInterfaceMethodName(),//函数式接口的默认方法
                        serializedLambda.getFunctionalInterfaceMethodSignature());//函数式接口的默认方法的方法签名[(方法参数类型)返回值类型]
                
                log.info(" Lambda Implementation: {}.{} ({})",
                        serializedLambda.getImplClass(),//Lambda所在的类
                        serializedLambda.getImplMethodName(),//Lambda所在的方法
                        serializedLambda.getImplMethodSignature());//Lambda的方法签名[(方法参数类型)返回值类型]
                IntStream.range(0, serializedLambda.getCapturedArgCount())//Lambda的外部参数
                        .forEach(i -> log.info("  with Captured Arg(" + i + "): '"
                                                           + serializedLambda.getCapturedArg(i)//参数的值
                                                           + ((serializedLambda.getCapturedArg(i) != null)
                                                                      ? "' (" + serializedLambda.getCapturedArg(i).getClass().getName() + ")"//参数类型
                                                                      : "")));
                
            }
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            throw new RuntimeException("Failed to find the Serialized form for the given Lambda Expression", e);
        }
       
    }
}
