package mrwho.mybatisplus;

import java.io.Serializable;
import java.util.function.Function;

/**
 * 继承接口 Serializable ，会使接口的 .getClass().getDeclaredMethods()出现 writeReplace()
 * @param <T>
 * @param <R>
 */
@FunctionalInterface
public interface SFunction<T, R> extends Function<T, R>, Serializable {
}
