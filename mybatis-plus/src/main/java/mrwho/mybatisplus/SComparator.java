package mrwho.mybatisplus;

import java.io.Serializable;
import java.util.Comparator;

@FunctionalInterface
public interface SComparator<T> extends Comparator<T>, Serializable {
}
