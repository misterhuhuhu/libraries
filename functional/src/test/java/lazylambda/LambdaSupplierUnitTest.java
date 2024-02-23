package lazylambda;

import org.junit.Test;
import org.mockito.Mockito;

import java.util.function.Supplier;

public class LambdaSupplierUnitTest {

    @Test
    public void whenCalledMultipleTimes_thenShouldBeCalledMultipleTimes() {
        @SuppressWarnings("unchecked") Supplier<String> mockedExpensiveFunction = Mockito.mock(Supplier.class);
        Mockito.when(mockedExpensiveFunction.get())//get()多次调用
            .thenReturn("expensive call");
        LambdaSupplier<String> testee = new LambdaSupplier<>(mockedExpensiveFunction);
        Mockito.verify(mockedExpensiveFunction, Mockito.never())
            .get();
        testee.getData();
        testee.getData();
        Mockito.verify(mockedExpensiveFunction, Mockito.times(2))
            .get();
    }

}
