package java9.language;


import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class Java9ObjectsAPIUnitTest {

    private List<String> aMethodReturningNullList(){
        return null;
    }

    @Test
    public void givenNullObject_whenRequireNonNullElse_thenElse(){
        /**
         * todo: 如果第一个参数不为 null，则返回第二个参数，否则返回第二个参数。如果两个参数都为 null，则引发 NullPointerException
         */
        List<String> aList = Objects.requireNonNullElse(
                aMethodReturningNullList(), Collections.emptyList());
        assertThat(aList, is(Collections.EMPTY_LIST));
    }

    private List<String> aMethodReturningNonNullList(){
        return List.of("item1", "item2");
    }

    @Test
    public void givenObject_whenRequireNonNullElse_thenObject(){
        List<String> aList = Objects.requireNonNullElse(
                aMethodReturningNonNullList(), Collections.emptyList());
        assertThat(aList, is(List.of("item1", "item2")));
    }

    @Test
    public void givenNull_whenRequireNonNullElse_thenException(){
        //报错
        Objects.<List>requireNonNullElse(null, null);
    }

    @Test
    public void givenObject_whenRequireNonNullElseGet_thenObject(){
        /**
         * todo: requireNonNullElseGet 此方法类似于 requireNonNullElse，只是第二个参数是 java.util.function.Supplier 接口，它允许对提供的集合进行延迟实例化
         */
        List<String> aList = Objects.<List>requireNonNullElseGet(null, List::of);
        assertThat(aList, is(List.of()));
    }

    @Test
    public void givenNumber_whenInvokeCheckIndex_thenNumber(){
        int length = 5;
        /**
         * todo checkIndex 此方法用于检查索引是否在给定长度内。如果 0 <= index < length，则返回索引。否则，它将引发 IndexOutOfBoundsException
         */
        assertThat(Objects.checkIndex(4, length), is(4));
    }

    @Test
    public void givenOutOfRangeNumber_whenInvokeCheckIndex_thenException(){
        int length = 5;
        Objects.checkIndex(5, length);
    }


    @Test
    public void givenSubRange_whenCheckFromToIndex_thenNumber(){
        int length = 6;
        /**
         * todo checkFromToIndex 该方法用于检查由 [fromIndex， toIndex） 形成的给定子范围是否在 [0， length] 形成的范围内。如果子范围有效，则返回下限
         */
        assertThat(Objects.checkFromToIndex(2,length,length), is(2));
    }

    @Test
    public void givenInvalidSubRange_whenCheckFromToIndex_thenException(){
        int length = 6;
        Objects.checkFromToIndex(2,7,length);
    }


    @Test
    public void givenSubRange_whenCheckFromIndexSize_thenNumber(){
        int length = 6;
        /**
         * todo :  checkFromIndexSize 子范围为 [fromIndex， fromIndex + size），此方法检查子范围是否在 [0， length] 形成的范围内
         */
        assertThat(Objects.checkFromIndexSize(2,3,length), is(2));
    }

    @Test
    public void givenInvalidSubRange_whenCheckFromIndexSize_thenException(){
        int length = 6;
        Objects.checkFromIndexSize(2, 6, length);
    }


}
