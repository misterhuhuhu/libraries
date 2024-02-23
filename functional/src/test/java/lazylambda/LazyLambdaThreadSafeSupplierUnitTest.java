package lazylambda;

import org.junit.Test;
import org.mockito.Mockito;
import org.mockito.stubbing.Answer;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.function.Supplier;

public class LazyLambdaThreadSafeSupplierUnitTest {

    @Test
    public void whenCalledMultipleTimes_thenShouldBeCalledOnlyOnce() {
        @SuppressWarnings("unchecked") Supplier<String> mockedExpensiveFunction = Mockito.mock(Supplier.class);
        Mockito.when(mockedExpensiveFunction.get())//线程安全的懒加载
            .thenReturn("expensive call");
        LazyLambdaThreadSafeSupplier<String> testee = new LazyLambdaThreadSafeSupplier<>(mockedExpensiveFunction);
        Mockito.verify(mockedExpensiveFunction, Mockito.never())
            .get();
        testee.getData();
        testee.getData();
        Mockito.verify(mockedExpensiveFunction, Mockito.times(1))
            .get();
    }

    @Test
    public void whenCalledMultipleTimesConcurrently_thenShouldBeCalledOnlyOnce() throws InterruptedException {
        @SuppressWarnings("unchecked") Supplier<String> mockedExpensiveFunction = Mockito.mock(Supplier.class);
        Mockito.when(mockedExpensiveFunction.get())
            .thenAnswer((Answer<String>) invocation -> {
                Thread.sleep(1000L);
                return "Late response!";
            });
        LazyLambdaThreadSafeSupplier<String> testee = new LazyLambdaThreadSafeSupplier<>(mockedExpensiveFunction);
        Mockito.verify(mockedExpensiveFunction, Mockito.never())
            .get();

        ExecutorService executorService = Executors.newFixedThreadPool(4);
        executorService.invokeAll(List.of(testee::getData, testee::getData));
        executorService.shutdown();
        if (!executorService.awaitTermination(5, TimeUnit.SECONDS)) {
            executorService.shutdownNow();
        }

        Mockito.verify(mockedExpensiveFunction, Mockito.times(1))
            .get();
    }

}
